package com.zhangl.myrxjava.simple2;

/**
 * Created by zhangl on 2019/3/6.
 */

public class ObservableSchedulers<T> extends Observable<T> {
    final Observable<T> source;
    final Schedulers schedulers;

    public ObservableSchedulers(Observable<T> source, Schedulers schedulers) {
        this.source = source;
        this.schedulers = schedulers;
    }

    @Override
    protected void subscribeActual(final Observer<T> observer) {

        schedulers.scheduleDirect(new SchedulerTask(observer));




    }

    private class SchedulerTask implements Runnable{
        final Observer<T> observer;
        public SchedulerTask(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void run() {
            // 线程池最终回来执行 Runnable -> 这行代码，会执行上游的 subscribe()
            // 而这个run方法在子线程中
            source.subscribe(observer);
        }
    }


}
