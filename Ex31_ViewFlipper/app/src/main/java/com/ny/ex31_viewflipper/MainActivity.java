package com.ny.ex31_viewflipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    ViewFlipper viewFlipper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        viewFlipper1 = findViewById(R.id.viewFlipper1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이전
                viewFlipper1.showPrevious();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음
                viewFlipper1.showNext();
            }
        });
        viewFlipper1.setFlipInterval(2000);
        viewFlipper1.startFlipping();
    }
}
