package com.zhangl.myrxjava.simple;

import android.support.annotation.NonNull;



/**
 * Created by zhangl on 2019/3/6.
 */

public class ObservableMap<T,R> extends Observable<R> {

    final Observable<T> source; //前面的 Observable
    final Function<T,R> function;   //当前转换

    public ObservableMap(Observable<T> source, Function<T,R> function){
        this.source = source;
        this.function = function;

    }




    @Override
    protected void subscribeActual(Observer<R> observer) {


        source.subscribe(new MapObserver(observer,function));
    }

    private class MapObserver<T> implements Observer<T>{

        final  Observer<R> observer;
        final Function<T,R> function;

        public MapObserver(Observer<R> source,Function<T,R> function){
            this.function = function;
            this.observer = source;
        }


        @Override
        public void onSubscribe() {
            observer.onSubscribe();

        }

        @Override
        public void onNext(@NonNull T item) {

            try {
                R applyR = function.apply(item);

                observer.onNext(applyR);
            } catch (Exception e) {
                e.printStackTrace();
                observer.onError(e);
            }

        }

        @Override
        public void onError(@NonNull Throwable e) {

            observer.onError(e);

        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }



}
