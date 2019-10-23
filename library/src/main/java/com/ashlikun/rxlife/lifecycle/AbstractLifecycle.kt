package com.ashlikun.rxlife.lifecycle

import android.os.Looper
import androidx.annotation.MainThread
import com.ashlikun.rxlife.scope.LifecycleScope
import com.ashlikun.rxlife.scope.Scope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.atomic.AtomicReference

/**
 * @author　　: 李坤
 * 创建时间: 2019/10/22 18:04
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：基础的感知Activity、Fragment生命周期的观察者
 */

abstract class AbstractLifecycle<T>(private val scope: Scope) : AtomicReference<T>(), Disposable {

    private val mObject = Object()

    private var isAddObserver: Boolean = false

    private val isMainThread: Boolean
        get() = Thread.currentThread() === Looper.getMainLooper().thread

    /**
     * 事件订阅时调用此方法
     */
    @Throws(Exception::class)
    protected fun addObserver() {
        //Lifecycle添加监听器需要在主线程执行
        if (isMainThread || scope !is LifecycleScope) {
            addObserverOnMain()
        } else {
            val any = mObject
            AndroidSchedulers.mainThread().scheduleDirect {
                addObserverOnMain()
                synchronized(any) {
                    isAddObserver = true
                    any.notifyAll()
                }
            }
            synchronized(any) {
                while (!isAddObserver) {
                    try {
                        any.wait()
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }

    @MainThread
    private fun addObserverOnMain() {
        scope.onScopeStart(this)
    }

    /**
     * onError/onComplete 时调用此方法
     */
    internal fun removeObserver() {
        //Lifecycle移除监听器需要在主线程执行
        if (isMainThread || scope !is LifecycleScope) {
            scope.onScopeEnd()
        } else {
            AndroidSchedulers.mainThread().scheduleDirect { this.removeObserver() }
        }
    }
}
