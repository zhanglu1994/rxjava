package com.zhangl.myrxjava.simple;

/**
 * Created by zhangl on 2019/3/6.
 */

public interface ObservableSource<T> {

    void subscribe(Observer<T> observer);


}
