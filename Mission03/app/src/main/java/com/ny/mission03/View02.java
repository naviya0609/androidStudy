package com.ny.mission03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class View02 extends AppCompatActivity {
    ImageView imageView3,imageView4;
    Button button3,button4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view02);

        imageView3 =findViewById(R.id.imageView3);
        imageView4 =findViewById(R.id.imageView4);
        button3 =findViewById(R.id.button3);
        button4 =findViewById(R.id.button4);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView3.setImageResource(R.drawable.pic4);
                imageView4.setImageResource(0);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView3.setImageResource(0);
                imageView4.setImageResource(R.drawable.pic4);
            }
        });
    }
}
