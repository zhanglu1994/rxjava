package com.zhangl.myrxjava.simple;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.zhangl.myrxjava.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {


    private ImageView iv;


    String imaUrl = "http://img15.3lian.com/2015/f2/57/d/93.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);


//        Observable.just(imaUrl)
//                .map(new Function<String, Bitmap>() {
//                    @Override
//                    public Bitmap apply(String s) throws Exception {
//
//                        URL url  = new URL(s);
//
//                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//
//                        InputStream inputStream = urlConnection.getInputStream();
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//
//                        return bitmap;
//                    }
//                })
//                .map(new Function<Bitmap, Bitmap>() {
//                    @Override
//                    public Bitmap apply(Bitmap bitmap) throws Exception {
//
//
//                        bitmap = createWatermark(bitmap,"哈哈哈 我贼帅");
//
//                        return bitmap;
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Bitmap>() {
//                    @Override
//                    public void accept(Bitmap bitmap) throws Exception {
//
//                        iv.setImageBitmap(bitmap);
//
//                    }
//                });


        // Observable:被观察对象
        // Observer:观察者
        // subscribe:订阅


//        Observable.just("xxurl")
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe() {
//                        Log.e("TAG","onSubscribe");
//                    }
//
//                    @Override
//                    public void onNext(@NonNull String item) {
//                        Log.e("TAG",item);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.e("TAG","onError");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e("TAG","onComplete");
//                    }
//                });


        new Thread(new Runnable() {
            @Override
            public void run() {

                Observable.just(imaUrl).map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String urlPath) throws Exception {
                        URL url = new URL(urlPath);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = urlConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        return bitmap;
                    }
                }).map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Exception {


                        return createWatermark(bitmap,"张芦贼帅");
                    }
                }).subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void onNext(final Bitmap item) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iv.setImageBitmap(item);
                            }
                        });
                    }
                });

            }
        }).start();


    }

    private Bitmap createWatermark(Bitmap bitmap, String mark) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint p = new Paint();
        // 水印颜色
        p.setColor(Color.parseColor("#C5FF0000"));
        // 水印字体大小
        p.setTextSize(150);
        //抗锯齿
        p.setAntiAlias(true);
        //绘制图像
        canvas.drawBitmap(bitmap, 0, 0, p);
        //绘制文字
        canvas.drawText(mark, 0, h / 2, p);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return bmp;
    }
}
