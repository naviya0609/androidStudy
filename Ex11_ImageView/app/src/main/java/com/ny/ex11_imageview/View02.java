package com.ny.ex11_imageview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class View02 extends AppCompatActivity  {
        Button btn5;
        ImageView imageView2;
        int[] images={
                R.drawable.boy,
                R.drawable.coffe,
                R.drawable.dog,
                R.drawable.donald
        };
        int i=0;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view2);
            btn5 = findViewById(R.id.btn5);
            imageView2 = findViewById(R.id.imageView2);

            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView2.setImageResource(images[i++%4]);
                }
            });
        }

}
