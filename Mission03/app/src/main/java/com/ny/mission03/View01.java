package com.ny.mission03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class View01 extends AppCompatActivity {
    ImageView imageView1,imageView2;
    Button button1,button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01);
        imageView1 =findViewById(R.id.imageView1);
        imageView2 =findViewById(R.id.imageView2);
        button1 =findViewById(R.id.button1);
        button2 =findViewById(R.id.button2);
        imageView2.setVisibility(ImageView.INVISIBLE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //imageView1.setImageResource(R.drawable.pic4);
                imageView1.setVisibility(ImageView.VISIBLE);
                imageView2.setVisibility(ImageView.INVISIBLE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //imageView1.setImageResource(R.drawable.pic4);
                imageView1.setVisibility(ImageView.INVISIBLE);
                imageView2.setVisibility(ImageView.VISIBLE);
            }
        });
    }
}
