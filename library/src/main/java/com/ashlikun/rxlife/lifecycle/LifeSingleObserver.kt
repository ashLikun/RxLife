package com.ashlikun.rxlife.lifecycle


import com.ashlikun.rxlife.scope.Scope
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.disposables.DisposableHelper
import io.reactivex.plugins.RxJavaPlugins

/**
 * @author　　: 李坤
 * 创建时间: 2019/10/22 18:08
 * 邮箱　　：496546144@qq.com
 *
 * 功能介绍：观察者
 * * 感知Activity、Fragment生命周期的观察者
 */

internal class LifeSingleObserver<T>(private val downstream: SingleObserver<in T>, scope: Scope) : AbstractLifecycle<Disposable>(scope), SingleObserver<T> {

    override fun onSubscribe(d: Disposable) {
        if (DisposableHelper.setOnce(this, d)) {
            try {
                addObserver()
                downstream.onSubscribe(d)
            } catch (ex: Throwable) {
                Exceptions.throwIfFatal(ex)
                d.dispose()
                onError(ex)
            }

        }
    }

    override fun onSuccess(t: T) {
        if (isDisposed) return
        lazySet(DisposableHelper.DISPOSED)
        try {
            removeObserver()
            downstream.onSuccess(t)
        } catch (e: Throwable) {
            Exceptions.throwIfFatal(e)
            RxJavaPlugins.onError(e)
        }

    }

    override fun onError(t: Throwable) {
        if (isDisposed) {
            RxJavaPlugins.onError(t)
            return
        }
        lazySet(DisposableHelper.DISPOSED)
        try {
            removeObserver()
            downstream.onError(t)
        } catch (e: Throwable) {
            Exceptions.throwIfFatal(e)
            RxJavaPlugins.onError(CompositeException(t, e))
        }

    }

    override fun isDisposed(): Boolean {
        return DisposableHelper.isDisposed(get())
    }

    override fun dispose() {
        DisposableHelper.dispose(this)
    }
}
