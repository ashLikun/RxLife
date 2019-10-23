[![Release](https://jitpack.io/v/ashLikun/RxLife.svg)](https://jitpack.io/#ashLikun/RxLife)

RxLife项目简介
   [RxLife](https://github.com/ashLikun/RxLife)，相较于[trello/RxLifecycle](https://github.com/trello/RxLifecycle)、[uber/AutoDispose](https://github.com/uber/AutoDispose)，具有如下优势：
   
    * 直接支持在主线程回调
    * 支持在子线程订阅观察者
    * 简单易用，学习成本低
    * 性能更优，在实现上更加简单
    
## 使用方法

build.gradle文件中添加:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
并且:

```gradle
dependencies {
    implementation 'com.github.ashLikun:RxLife:{latest version}'
}
```

#Usage

### 1、Activity/Fragment
Activity/Fragment销毁时，自动关闭RxJava管道

```java
Observable.timer(5, TimeUnit.SECONDS)
    .as(RxLife.as(this))     //此时的this Activity/Fragment对象
    .subscribe(aLong -> {
        Log.e("LJX", "accept =" + aLong);
    });
```

### 2、View
View被移除时，自动关闭RxJava管道
```java
Observable.timer(5, TimeUnit.SECONDS)
    .as(RxLife.as(this))  //此时的this 为View对象
    .subscribe(aLong -> {
        Log.e("LJX", "accept =" + aLong);
    });

```

### 3、ViewModel
Activity/Fragment销毁时，自动关闭RxJava管道，ViewModel需要继承`ScopeViewModel`类，如下

```java
public class MyViewModel extends ScopeViewModel {

    public MyViewModel(@NonNull Application application) {
        super(application);
        Observable.interval(1, 1, TimeUnit.SECONDS)
            .as(RxLife.asOnMain(this))
            .subscribe(aLong -> {
                Log.e("LJX", "MyViewModel aLong=" + aLong);
            });
    }
}
```

**注意:** 一定要在Activity/Fragment通过以下方式获取ViewModel对象，否则RxLife接收不到生命周期的回调

```java

MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);

```

### 4、任意类
Activity/Fragment销毁时，自动关闭RxJava管道，任意类需要继承`BaseScope`类，如P层：

```java
public class Presenter extends BaseScope {

    public Presenter(LifecycleOwner owner) {
        super(owner); //添加生命周期监听
        Observable.interval(1, 1, TimeUnit.SECONDS)
            .life(RxLife.life(this)) //这里的this 为Scope接口对象
            .subscribe(aLong -> {
                Log.e("LJX", "accept aLong=" + aLong);
            });
    }
}
```

### 5、kotlin用户

由于`as`是kotlin中的一个关键字，所以在kotlin中，我们并不能直接使用`as(RxLife.as(this))`,可以如下编写

```java
Observable.intervalRange(1, 100, 0, 200, TimeUnit.MILLISECONDS)
    .`as`(RxLife.`as`(this))
    .subscribe { aLong ->
        Log.e("LJX", "accept=" + aLong)
    }
```

当然，相信没多少人会喜欢这种写法，故，RxLife针对kotlin用户，新增更为便捷的写法，如下：

```java
Observable.intervalRange(1, 100, 0, 200, TimeUnit.MILLISECONDS)
    .life(this)
    .subscribe { aLong ->
        Log.e("LJX", "accept=" + aLong)
    }
```
使用`life` 操作符替代`as`操作符即可，其它均一样


### 6、小彩蛋

#### asOnMain操作符
RxLife还提供了`asOnMain`操作符，它可以指定下游的观察者在主线程中回调，如下：
```java
Observable.timer(5, TimeUnit.SECONDS)
    .life(RxLife.lifeOnMain(this))
    .subscribe(aLong -> {
        //在主线程回调
       Log.e("LJX", "accept =" + aLong);
    });

        //等价于
Observable.timer(5, TimeUnit.SECONDS)
    .observeOn(AndroidSchedulers.mainThread())
    .life(RxLife.life(this))
    .subscribe(aLong -> {
        //在主线程回调
        Log.e("LJX", "accept =" + aLong);
    });

```

**注意:** RxLife类里面life操作符，皆适用于Flowable、ParallelFlowable、Observable、Single、Maybe、Completable这6个被观察者对象


### 混淆

RxLife作为开源库，可混淆，也可不混淆，如果不希望被混淆，请在proguard-rules.pro文件添加以下代码

```java
-keep class com.ashlikun.rxlife.**{*;}
```


# 更新日志

