package com.ashlikun.rxlife.scope

import android.os.Build
import android.view.View
import android.view.View.OnAttachStateChangeListener
import com.ashlikun.rxlife.OutsideScopeException

import io.reactivex.disposables.Disposable

/**
 * @author　　: 李坤
 * 创建时间: 2019/10/22 18:00
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：View的作用域
 */

class ViewScope private constructor(private val view: View, private val ignoreAttach: Boolean//忽略View是否添加到Window
) : Scope, OnAttachStateChangeListener {
    private var disposable: Disposable? = null

    override fun onScopeStart(d: Disposable) {
        disposable = d
        val isAttached = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && view.isAttachedToWindow || view.windowToken != null
        if (!isAttached && !ignoreAttach)
            throw OutsideScopeException("View is not attached!")
        view.addOnAttachStateChangeListener(this)
    }

    override fun onScopeEnd() {
        val view = this.view ?: return
        view.removeOnAttachStateChangeListener(this)
    }

    override fun onViewAttachedToWindow(v: View) {

    }

    override fun onViewDetachedFromWindow(v: View) {
        disposable!!.dispose()
        v.removeOnAttachStateChangeListener(this)
    }

    companion object {

        /**
         * @param view         目标View
         * @param ignoreAttach 忽略View是否添加到Window，默认为false，即不忽略
         * @return ViewScope
         */
        internal fun from(view: View, ignoreAttach: Boolean): ViewScope {
            return ViewScope(view, ignoreAttach)
        }
    }
}
