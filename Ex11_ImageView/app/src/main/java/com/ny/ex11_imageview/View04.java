package com.ny.ex11_imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

//인터넷
//1. Manifest에서 반드시 인터넷 사용허가 받아야함
//2. 네트워크 처리의 기본은 Thread 처리할것
//3. Thread 처리시 handler 사용하것

public class View04 extends AppCompatActivity {
    Button btn7;
    ImageView imageView4;
    Handler handler = new Handler(); //android 객체
    Bitmap bitmap; //인터넷 상의 이미지는 bitmap


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view4);
        btn7 = findViewById(R.id.btn7);
        imageView4 = findViewById(R.id.imageView4);

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                           // URL url = new URL("인터넷주소");
                            URL url = new URL("https://post-phinf.pstatic.net/MjAxOTA1MjBfMjQy/MDAxNTU4MzM2NzI2MTQ4.XdnTQ-zDQzqj1Z1v1bhodhaXzsY1WndWElxxHjmJYJMg.P7y83sc58QJQ_1lQ1kj8qghBz-9NgYSb4Sp9L2OtNigg.PNG/%EB%B3%B4%EB%B0%B0%EB%B3%B4%EB%A6%AC.png?type=w1200");
                            InputStream is = url.openStream();       //정보읽기
                            BufferedInputStream bis = new BufferedInputStream(is); //스트림 체인방식
                            bitmap = BitmapFactory.decodeStream(bis);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                imageView4.setImageBitmap(bitmap);
                                }
                            });

                        }catch (Exception e){

                        }
                    }
                }).start(); //start 만나면 run실행
            }
        });

    }
}
