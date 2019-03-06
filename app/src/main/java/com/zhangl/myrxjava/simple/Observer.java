package com.zhangl.myrxjava.simple;

import android.support.annotation.NonNull;

/**
 * Created by zhangl on 2019/3/6.
 *
 * 被观察者
 */

public interface Observer<T> {

    void onSubscribe();
    void onNext(@NonNull T item);
    void onError(@NonNull Throwable e);
    void onComplete();

}
