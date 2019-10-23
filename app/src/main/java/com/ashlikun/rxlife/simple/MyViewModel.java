package com.ashlikun.rxlife.simple;

import android.app.Application;
import android.util.Log;

import com.ashlikun.rxlife.RxLife;
import com.ashlikun.rxlife.scope.ScopeViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * User: ljx
 * Date: 2019-05-31
 * Time: 21:50
 */
public class MyViewModel extends ScopeViewModel {

    public MyViewModel( Application application) {
        super(application);
        Observable.interval(1, 1, TimeUnit.SECONDS)
            .as(RxLife.lifeOnMain(this))
            .subscribe(aLong -> {
                Log.e("LJX", "MyViewModel aLong=" + aLong);
            });
    }
}
