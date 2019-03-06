package com.zhangl.myrxjava.simple;

/**
 * Created by zhangl on 2019/3/6.
 */

public interface Function<T,R> {

    R apply(T t) throws Exception;

}
