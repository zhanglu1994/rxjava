package com.zhangl.myrxjava.simple2;

/**
 * Created by zhangl on 2019/3/6.
 */

public interface Consumer<T> {

    void onNext(T item);

}
