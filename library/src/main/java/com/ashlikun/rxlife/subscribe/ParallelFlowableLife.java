package com.ashlikun.rxlife.subscribe;

import com.ashlikun.rxlife.lifecycle.LifeConditionalSubscriber;
import com.ashlikun.rxlife.lifecycle.LifeSubscriber;
import com.ashlikun.rxlife.scope.Scope;

import org.reactivestreams.Subscriber;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.parallel.ParallelFlowable;

/**
 * @author　　: 李坤
 * 创建时间: 2019/10/23 10:14
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：替换Rx的订阅方法
 * 这里用java实现是为了兼容RxJava的语法糖
 */
public class ParallelFlowableLife<T> {

    private ParallelFlowable<T> upStream;
    private Scope scope;
    private boolean onMain;

    public ParallelFlowableLife(ParallelFlowable<T> upStream, Scope scope, boolean onMain) {
        this.upStream = upStream;
        this.scope = scope;
        this.onMain = onMain;
    }

    @SuppressWarnings("unchecked")
    public void subscribe(@NonNull Subscriber<? super T>[] subscribers) {
        if (!validate(subscribers)) {
            return;
        }

        int n = subscribers.length;

        Subscriber<? super T>[] parents = new Subscriber[n];

        for (int i = 0; i < n; i++) {
            Subscriber<? super T> a = subscribers[i];
            if (a instanceof ConditionalSubscriber) {
                parents[i] = new LifeConditionalSubscriber<>((ConditionalSubscriber<? super T>) a, scope);
            } else {
                parents[i] = new LifeSubscriber<>(a, scope);
            }
        }
        ParallelFlowable<T> upStream = this.upStream;
        if (onMain) {
            upStream = upStream.runOn(AndroidSchedulers.mainThread());
        }
        upStream.subscribe(parents);
    }

    private int parallelism() {
        return upStream.parallelism();
    }

    private boolean validate(@NonNull Subscriber<?>[] subscribers) {
        int p = parallelism();
        if (subscribers.length != p) {
            Throwable iae = new IllegalArgumentException("parallelism = " + p + ", subscribers = " + subscribers.length);
            for (Subscriber<?> s : subscribers) {
                EmptySubscription.error(iae, s);
            }
            return false;
        }
        return true;
    }
}
