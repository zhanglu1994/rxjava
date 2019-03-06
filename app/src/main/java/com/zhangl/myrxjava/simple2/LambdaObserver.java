package com.zhangl.myrxjava.simple2;

import android.support.annotation.NonNull;


/**
 * Created by zhangl on 2019/3/6.
 */

public class LambdaObserver<T> implements Observer<T> {


    private Consumer<T> onNext;


    public LambdaObserver(Consumer<T> onNext) {
        this.onNext = onNext;
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onNext(@NonNull T item) {

        onNext.onNext(item);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
