package com.ashlikun.rxlife

import android.view.View
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleOwner
import com.ashlikun.rxlife.scope.LifecycleScope
import com.ashlikun.rxlife.scope.Scope
import com.ashlikun.rxlife.scope.ViewScope
import com.ashlikun.rxlife.subscribe.*
import io.reactivex.*
import io.reactivex.parallel.ParallelFlowable

/**
 * @author　　: 李坤
 * 创建时间: 2019/10/22 16:17
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：RxLife的核心类
 */

object RxLife {
    @JvmStatic
    fun <T> life(owner: LifecycleOwner): RxConverter<T> {
        return life(owner, Event.ON_DESTROY, false)
    }
    @JvmStatic
    fun <T> life(owner: LifecycleOwner, event: Event): RxConverter<T> {
        return life(owner, event, false)
    }
    @JvmStatic
    fun <T> lifeOnMain(owner: LifecycleOwner): RxConverter<T> {
        return life(owner, Event.ON_DESTROY, true)
    }
    @JvmStatic
    fun <T> lifeOnMain(owner: LifecycleOwner, event: Event): RxConverter<T> {
        return life(owner, event, true)
    }
    @JvmStatic
    private fun <T> life(owner: LifecycleOwner, event: Event, onMain: Boolean): RxConverter<T> {
        return life(LifecycleScope.from(owner, event), onMain)
    }
    @JvmStatic
    fun <T> life(view: View): RxConverter<T> {
        return life(ViewScope.from(view, false), false)
    }

    /**
     * @param view         目标View
     * @param ignoreAttach 忽略View是否添加到Window，默认为false，即不忽略
     * @return RxConverter
     */
    @JvmStatic
    fun <T> life(view: View, ignoreAttach: Boolean): RxConverter<T> {
        return life(ViewScope.from(view, ignoreAttach), false)
    }
    @JvmStatic
    fun <T> lifeOnMain(view: View): RxConverter<T> {
        return life(ViewScope.from(view, false), true)
    }

    /**
     * @param view         目标View
     * @param ignoreAttach 忽略View是否添加到Window，默认为false，即不忽略
     * @return RxConverter
     */
    @JvmStatic
    fun <T> lifeOnMain(view: View, ignoreAttach: Boolean): RxConverter<T> {
        return life(ViewScope.from(view, ignoreAttach), true)
    }
    @JvmStatic
    fun <T> life(scope: Scope): RxConverter<T> {
        return life(scope, false)
    }
    @JvmStatic
    fun <T> lifeOnMain(scope: Scope): RxConverter<T> {
        return life(scope, true)
    }

    /**
     * 主要方法RxJava 的转换器
     * @param
     */
    @JvmStatic
    private fun <T> life(scope: Scope, onMain: Boolean): RxConverter<T> {
        return object : RxConverter<T> {

            override fun apply(upstream: Observable<T>): ObservableLife<T> {
                return ObservableLife(upstream, scope, onMain)
            }

            override fun apply(upstream: Flowable<T>): FlowableLife<T> {
                return FlowableLife(upstream, scope, onMain)
            }

            override fun apply(upstream: ParallelFlowable<T>): ParallelFlowableLife<T> {
                return ParallelFlowableLife(upstream, scope, onMain)
            }

            override fun apply(upstream: Maybe<T>): MaybeLife<T> {
                return MaybeLife(upstream, scope, onMain)
            }

            override fun apply(upstream: Single<T>): SingleLife<T> {
                return SingleLife(upstream, scope, onMain)
            }

            override fun apply(upstream: Completable): CompletableLife {
                return CompletableLife(upstream, scope, onMain)
            }
        }
    }
}
