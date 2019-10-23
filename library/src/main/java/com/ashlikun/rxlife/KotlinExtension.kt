package com.ashlikun.rxlife

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ashlikun.rxlife.scope.Scope
import com.ashlikun.rxlife.subscribe.*
import io.reactivex.*
import io.reactivex.parallel.ParallelFlowable


/**
 * @author　　: 李坤
 * 创建时间: 2019/10/22 16:16
 * 邮箱　　：496546144@qq.com
 *
 * 功能介绍：对RxJava2对于Kotlin的as方法兼容
 * [Observable. as] + [RxLife. as]
 * 要在订阅方法上一步调用就行
 */

fun <T> Observable<T>.life(owner: LifecycleOwner): ObservableLife<T> =
        this.`as`(RxLife.life<T>(owner))

fun <T> Flowable<T>.life(owner: LifecycleOwner): FlowableLife<T> =
        this.`as`(RxLife.life(owner))

fun <T> Maybe<T>.life(owner: LifecycleOwner): MaybeLife<T> =
        this.`as`(RxLife.life<T>(owner))

fun Completable.life(owner: LifecycleOwner): CompletableLife =
        this.`as`(RxLife.life<Any>(owner))

fun <T> Single<T>.life(owner: LifecycleOwner): SingleLife<T> =
        this.`as`(RxLife.life<T>(owner))

fun <T> ParallelFlowable<T>.life(owner: LifecycleOwner): ParallelFlowableLife<T> =
        this.`as`(RxLife.life<T>(owner))

fun <T> Observable<T>.life(view: View): ObservableLife<T> =
        this.`as`(RxLife.life<T>(view))

fun <T> Flowable<T>.life(view: View): FlowableLife<T> =
        this.`as`(RxLife.life(view))

fun <T> Maybe<T>.life(view: View): MaybeLife<T> =
        this.`as`(RxLife.life<T>(view))

fun Completable.life(view: View): CompletableLife =
        this.`as`(RxLife.life<Any>(view))

fun <T> Single<T>.life(view: View): SingleLife<T> =
        this.`as`(RxLife.life<T>(view))

fun <T> ParallelFlowable<T>.life(view: View): ParallelFlowableLife<T> =
        this.`as`(RxLife.life<T>(view))

fun <T> Observable<T>.life(view: View, ignoreAttach: Boolean): ObservableLife<T> =
        this.`as`(RxLife.life<T>(view, ignoreAttach))

fun <T> Flowable<T>.life(view: View, ignoreAttach: Boolean): FlowableLife<T> =
        this.`as`(RxLife.life(view, ignoreAttach))

fun <T> Maybe<T>.life(view: View, ignoreAttach: Boolean): MaybeLife<T> =
        this.`as`(RxLife.life<T>(view, ignoreAttach))

fun Completable.life(view: View, ignoreAttach: Boolean): CompletableLife =
        this.`as`(RxLife.life<Any>(view, ignoreAttach))

fun <T> Single<T>.life(view: View, ignoreAttach: Boolean): SingleLife<T> =
        this.`as`(RxLife.life<T>(view, ignoreAttach))

fun <T> ParallelFlowable<T>.life(view: View, ignoreAttach: Boolean): ParallelFlowableLife<T> =
        this.`as`(RxLife.life<T>(view, ignoreAttach))

fun <T> Observable<T>.life(scope: Scope): ObservableLife<T> =
        this.`as`(RxLife.life<T>(scope))

fun <T> Flowable<T>.life(scope: Scope): FlowableLife<T> =
        this.`as`(RxLife.life(scope))

fun <T> Maybe<T>.life(scope: Scope): MaybeLife<T> =
        this.`as`(RxLife.life<T>(scope))

fun Completable.life(scope: Scope): CompletableLife =
        this.`as`(RxLife.life<Any>(scope))

fun <T> Single<T>.life(scope: Scope): SingleLife<T> =
        this.`as`(RxLife.life<T>(scope))

fun <T> ParallelFlowable<T>.life(scope: Scope): ParallelFlowableLife<T> =
        this.`as`(RxLife.life<T>(scope))


fun <T> Observable<T>.life(owner: LifecycleOwner, event: Lifecycle.Event): ObservableLife<T> =
        this.`as`(RxLife.life<T>(owner, event))

fun <T> Flowable<T>.life(owner: LifecycleOwner, event: Lifecycle.Event): FlowableLife<T> =
        this.`as`(RxLife.life(owner, event))

fun <T> Maybe<T>.life(owner: LifecycleOwner, event: Lifecycle.Event): MaybeLife<T> =
        this.`as`(RxLife.life<T>(owner, event))

fun Completable.life(owner: LifecycleOwner, event: Lifecycle.Event): CompletableLife =
        this.`as`(RxLife.life<Any>(owner, event))

fun <T> Single<T>.life(owner: LifecycleOwner, event: Lifecycle.Event): SingleLife<T> =
        this.`as`(RxLife.life<T>(owner, event))

fun <T> ParallelFlowable<T>.life(owner: LifecycleOwner, event: Lifecycle.Event): ParallelFlowableLife<T> =
        this.`as`(RxLife.life<T>(owner, event))


/**
 * [Observable. as] + [RxLife.lifeOnMain]
 * Life并且添加到main线程
 */
fun <T> Observable<T>.lifeOnMain(owner: LifecycleOwner): ObservableLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(owner))

fun <T> Flowable<T>.lifeOnMain(owner: LifecycleOwner): FlowableLife<T> =
        this.`as`(RxLife.lifeOnMain(owner))

fun <T> Maybe<T>.lifeOnMain(owner: LifecycleOwner): MaybeLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(owner))

fun Completable.lifeOnMain(owner: LifecycleOwner): CompletableLife =
        this.`as`(RxLife.lifeOnMain<Any>(owner))

fun <T> Single<T>.lifeOnMain(owner: LifecycleOwner): SingleLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(owner))

fun <T> ParallelFlowable<T>.lifeOnMain(owner: LifecycleOwner): ParallelFlowableLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(owner))


fun <T> Observable<T>.lifeOnMain(view: View): ObservableLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(view))

fun <T> Flowable<T>.lifeOnMain(view: View): FlowableLife<T> =
        this.`as`(RxLife.lifeOnMain(view))

fun <T> Maybe<T>.lifeOnMain(view: View): MaybeLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(view))

fun Completable.lifeOnMain(view: View): CompletableLife =
        this.`as`(RxLife.lifeOnMain<Any>(view))

fun <T> Single<T>.lifeOnMain(view: View): SingleLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(view))

fun <T> ParallelFlowable<T>.lifeOnMain(view: View): ParallelFlowableLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(view))

fun <T> Observable<T>.lifeOnMain(view: View, ignoreAttach: Boolean): ObservableLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(view, ignoreAttach))

fun <T> Flowable<T>.lifeOnMain(view: View, ignoreAttach: Boolean): FlowableLife<T> =
        this.`as`(RxLife.lifeOnMain(view, ignoreAttach))

fun <T> Maybe<T>.lifeOnMain(view: View, ignoreAttach: Boolean): MaybeLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(view, ignoreAttach))

fun Completable.lifeOnMain(view: View, ignoreAttach: Boolean): CompletableLife =
        this.`as`(RxLife.lifeOnMain<Any>(view, ignoreAttach))

fun <T> Single<T>.lifeOnMain(view: View, ignoreAttach: Boolean): SingleLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(view, ignoreAttach))

fun <T> ParallelFlowable<T>.lifeOnMain(view: View, ignoreAttach: Boolean): ParallelFlowableLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(view, ignoreAttach))

fun <T> Observable<T>.lifeOnMain(owner: LifecycleOwner, event: Lifecycle.Event): ObservableLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(owner, event))

fun <T> Flowable<T>.lifeOnMain(owner: LifecycleOwner, event: Lifecycle.Event): FlowableLife<T> =
        this.`as`(RxLife.lifeOnMain(owner, event))

fun <T> Maybe<T>.lifeOnMain(owner: LifecycleOwner, event: Lifecycle.Event): MaybeLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(owner, event))

fun Completable.lifeOnMain(owner: LifecycleOwner, event: Lifecycle.Event): CompletableLife =
        this.`as`(RxLife.lifeOnMain<Any>(owner, event))

fun <T> Single<T>.lifeOnMain(owner: LifecycleOwner, event: Lifecycle.Event): SingleLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(owner, event))

fun <T> ParallelFlowable<T>.lifeOnMain(owner: LifecycleOwner, event: Lifecycle.Event): ParallelFlowableLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(owner, event))


fun <T> Observable<T>.lifeOnMain(scope: Scope): ObservableLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(scope))

fun <T> Flowable<T>.lifeOnMain(scope: Scope): FlowableLife<T> =
        this.`as`(RxLife.lifeOnMain(scope))

fun <T> Maybe<T>.lifeOnMain(scope: Scope): MaybeLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(scope))

fun Completable.lifeOnMain(scope: Scope): CompletableLife =
        this.`as`(RxLife.lifeOnMain<Any>(scope))

fun <T> Single<T>.lifeOnMain(scope: Scope): SingleLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(scope))

fun <T> ParallelFlowable<T>.lifeOnMain(scope: Scope): ParallelFlowableLife<T> =
        this.`as`(RxLife.lifeOnMain<T>(scope))


