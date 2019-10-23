package com.ashlikun.rxlife

import com.ashlikun.rxlife.subscribe.*
import io.reactivex.*
import io.reactivex.parallel.ParallelFlowableConverter

/**
 * User: ljx
 * Date: 2019/4/18
 * Time: 18:40
 */
 interface RxConverter<T> : ObservableConverter<T, ObservableLife<T>>,
        FlowableConverter<T, FlowableLife<T>>,
        ParallelFlowableConverter<T, ParallelFlowableLife<T>>,
        MaybeConverter<T, MaybeLife<T>>,
        SingleConverter<T, SingleLife<T>>,
        CompletableConverter<CompletableLife>
