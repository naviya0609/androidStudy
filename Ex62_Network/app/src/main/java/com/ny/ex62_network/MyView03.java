package com.ny.ex62_network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyView03 extends AppCompatActivity {
    Button button6;
    ImageView imageView1;
    Bitmap bitmap;
    static final String imgUrl = "http://172.16.14.1:8090/server01/images/cat.png";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myview03);

        button6 = findViewById(R.id.button6);
        imageView1 = findViewById(R.id.imageView1);

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downImage(imgUrl);
                handler.sendEmptyMessage(0);
            }
        });
    }
    private void downImage(final String file){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL(file);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.connect();
                    if(conn != null){
                        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                            InputStream is = conn.getInputStream();
                            bitmap = BitmapFactory.decodeStream(is);
                            conn.disconnect();
                        }
                    }
                }catch (Exception e){
                }
            }
        }).start();
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            handler.sendEmptyMessage(0);
            imageView1.setImageBitmap(bitmap);
        }
    };
}
