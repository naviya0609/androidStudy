package com.ny.ex37_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ThirdActivity extends AppCompatActivity {
    ImageView imageView10 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        imageView10 = findViewById(R.id.imageView10);

        Intent s_intent = getIntent();
        int index = s_intent.getIntExtra("index",0);
        int[] img = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,
                R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,
                R.drawable.pic7,R.drawable.pic8,R.drawable.pic9 };

        imageView10.setImageResource(img[index]);
    }
}
