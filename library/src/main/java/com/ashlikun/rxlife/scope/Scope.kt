package com.ashlikun.rxlife.scope

import io.reactivex.disposables.Disposable

/**
 * RxJava作用域
 * User: ljx
 * Date: 2019-05-26
 * Time: 18:17
 */
interface Scope {

    /**
     * 订阅事件时,回调本方法，即在onSubscribe(Disposable d)方法执行时回调本方法
     *
     * @param d Disposable
     */
    fun onScopeStart(d: Disposable)

    /**
     * onError/onComplete 时调回调此方法，即事件正常结束时回调
     */
    fun onScopeEnd()
}
