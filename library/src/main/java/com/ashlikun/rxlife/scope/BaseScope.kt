package com.ashlikun.rxlife.scope


import androidx.lifecycle.GenericLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ashlikun.rxlife.scope.Scope

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author　　: 李坤
 * 创建时间: 2019/10/22 18:00
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：基础的作用域
 */

open class BaseScope(owner: LifecycleOwner) : Scope, GenericLifecycleObserver {

    private var mDisposables: CompositeDisposable? = null

    init {
        owner.lifecycle.addObserver(this)
    }

    override fun onScopeStart(d: Disposable) {
        addDisposable(d)
    }

    override fun onScopeEnd() {

    }

    private fun addDisposable(disposable: Disposable) {
        var disposables = mDisposables
        if (disposables == null) {
            mDisposables = CompositeDisposable()
            disposables = mDisposables
        }
        disposables!!.add(disposable)
    }

    private fun dispose() {
        val disposables = mDisposables ?: return
        disposables.dispose()
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        //Activity/Fragment 生命周期回调
        if (event == Lifecycle.Event.ON_DESTROY) {  //Activity/Fragment 销毁
            source.lifecycle.removeObserver(this)
            dispose() //中断RxJava管道
        }
    }
}
