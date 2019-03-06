package com.zhangl.myrxjava.simple;

/**
 * Created by zhangl on 2019/3/6.
 */

public interface Consumer<T> {

    void onNext(T item);

}
