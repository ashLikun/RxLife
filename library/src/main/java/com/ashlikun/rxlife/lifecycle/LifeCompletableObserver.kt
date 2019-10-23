package com.ashlikun.rxlife.lifecycle


import com.ashlikun.rxlife.scope.Scope
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.disposables.DisposableHelper
import io.reactivex.plugins.RxJavaPlugins

/**
 * @author　　: 李坤
 * 创建时间: 2019/10/22 18:09
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：观察者
 * 感知Activity、Fragment生命周期的观察者
 */

internal class LifeCompletableObserver(private val downstream: CompletableObserver, scope: Scope) : AbstractLifecycle<Disposable>(scope), CompletableObserver {

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

    override fun onError(t: Throwable) {
        if (isDisposed) return
        lazySet(DisposableHelper.DISPOSED)
        try {
            removeObserver()
            downstream.onError(t)
        } catch (ex: Throwable) {
            Exceptions.throwIfFatal(ex)
            RxJavaPlugins.onError(ex)
        }

    }

    override fun onComplete() {
        if (isDisposed) return
        lazySet(DisposableHelper.DISPOSED)
        try {
            removeObserver()
            downstream.onComplete()
        } catch (ex: Throwable) {
            Exceptions.throwIfFatal(ex)
            RxJavaPlugins.onError(ex)
        }

    }

    override fun dispose() {
        DisposableHelper.dispose(this)
    }

    override fun isDisposed(): Boolean {
        return DisposableHelper.isDisposed(get())
    }
}
