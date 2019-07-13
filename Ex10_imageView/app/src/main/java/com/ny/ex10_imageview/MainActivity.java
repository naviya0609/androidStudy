package com.ny.ex10_imageview;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView2,imageView4, imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        imageView2 = findViewById(R.id.imageView2);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);

        //invisible>> visible 처리
        imageView2.setVisibility(ImageView.VISIBLE);
        imageView4.setVisibility(ImageView.VISIBLE);

        //이미지 src 지정하기
        //1.
        // imageView5.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.rabbit));// resource도 R.drawable.rabbit 객체 상수로 가져옴 BitmapFactory.decodeResource(getResources(),R.drawable.rabbit)
        //2.
        imageView5.setImageResource(R.drawable.rabbit);
    }

}
