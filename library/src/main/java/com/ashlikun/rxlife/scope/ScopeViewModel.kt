package com.ashlikun.rxlife.scope

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ashlikun.rxlife.scope.Scope

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author　　: 李坤
 * 创建时间: 2019/10/22 18:01
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：ViewModel 作用域
 */

open class ScopeViewModel(application: Application) : AndroidViewModel(application), Scope {

    private var mDisposables: CompositeDisposable? = null

    override fun onScopeStart(d: Disposable) {
        addDisposable(d)//订阅事件时回调
    }

    override fun onScopeEnd() {
        //事件正常结束时回调
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

    override fun onCleared() {
        super.onCleared() //Activity/Fragment 销毁时回调
        dispose() //中断RxJava管道
    }
}
