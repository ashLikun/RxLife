package com.ashlikun.rxlife.scope


import androidx.lifecycle.GenericLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleOwner

import io.reactivex.disposables.Disposable

/**
 * @author　　: 李坤
 * 创建时间: 2019/10/22 17:59
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：Activity/Fragment作用域
 */

class LifecycleScope private constructor(private val lifecycle: Lifecycle, private val event: Event)
    : Scope, GenericLifecycleObserver {
    private var disposable: Disposable? = null

    override fun onScopeStart(d: Disposable) {
        this.disposable = d
        onScopeEnd()
        lifecycle.addObserver(this)
    }

    override fun onScopeEnd() {
        lifecycle.removeObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Event) {
        if (event == this.event) {
            disposable!!.dispose()
            source.lifecycle.removeObserver(this)
        }
    }

    companion object {

        internal fun from(owner: LifecycleOwner, event: Event): LifecycleScope {
            return LifecycleScope(owner.lifecycle, event)
        }
    }
}
