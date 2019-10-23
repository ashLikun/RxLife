package com.ashlikun.rxlife.lifecycle


import com.ashlikun.rxlife.scope.Scope
import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.subscriptions.SubscriptionHelper
import io.reactivex.plugins.RxJavaPlugins
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * @author　　: 李坤
 * 创建时间: 2019/10/22 16:40
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：观察者
 * 感知Activity、Fragment生命周期的观察者
 */

internal class LifeSubscriber<T>(private val downstream: Subscriber<in T>, scope: Scope)
    : AbstractLifecycle<Subscription>(scope), Subscriber<T> {

    override fun onSubscribe(s: Subscription) {
        if (SubscriptionHelper.setOnce(this, s)) {
            try {
                addObserver()
                downstream.onSubscribe(s)
            } catch (ex: Throwable) {
                Exceptions.throwIfFatal(ex)
                s.cancel()
                onError(ex)
            }

        }
    }

    override fun onNext(t: T) {
        if (isDisposed) return
        try {
            downstream.onNext(t)
        } catch (e: Throwable) {
            Exceptions.throwIfFatal(e)
            get().cancel()
            onError(e)
        }

    }

    override fun onError(t: Throwable) {
        if (isDisposed) {
            RxJavaPlugins.onError(t)
            return
        }
        lazySet(SubscriptionHelper.CANCELLED)
        try {
            removeObserver()
            downstream.onError(t)
        } catch (e: Throwable) {
            Exceptions.throwIfFatal(e)
            RxJavaPlugins.onError(CompositeException(t, e))
        }

    }

    override fun onComplete() {
        if (isDisposed) return
        lazySet(SubscriptionHelper.CANCELLED)
        try {
            removeObserver()
            downstream.onComplete()
        } catch (e: Throwable) {
            Exceptions.throwIfFatal(e)
            RxJavaPlugins.onError(e)
        }

    }

    override fun isDisposed(): Boolean {
        return get() === SubscriptionHelper.CANCELLED
    }

    override fun dispose() {
        SubscriptionHelper.cancel(this)
    }
}
