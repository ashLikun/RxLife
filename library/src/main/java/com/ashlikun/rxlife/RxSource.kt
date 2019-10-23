/**
 * Copyright (c) 2016-present, RxJava Contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */
package com.ashlikun.rxlife

import com.ashlikun.rxlife.scope.Scope
import io.reactivex.disposables.Disposable

/**
 * @author　　: 李坤
 * 创建时间: 2019/10/22 16:46
 * 邮箱　　：496546144@qq.com
 *
 *
 * 功能介绍：替换Rx的订阅方法
 */

abstract class RxSource<E>(var scope: Scope, var onMain: Boolean) {

    abstract fun subscribe(): Disposable
    /**
     * Subscribes the given Observer to this ObservableSource instance.
     *
     * @param observer the Observer, not null
     * @throws NullPointerException if `observer` is null
     */
    abstract fun subscribe(observer: E): Disposable

    fun <O : E> subscribeWith(observer: O): O {
        subscribe(observer)
        return observer
    }
}
