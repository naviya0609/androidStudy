package com.ny.ex15_ratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {
    RatingBar ratingBar1,ratingBar2,ratingBar3;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        ratingBar1 = findViewById(R.id.ratingBar);
        ratingBar2 = findViewById(R.id.ratingBar2);
        ratingBar3 = findViewById(R.id.ratingBar3);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ratingBar1.setRating(ratingBar1.getRating() + ratingBar1.getStepSize());
               //default 0.5 stepsize 1이면 한개씩
                ratingBar2.setRating(ratingBar2.getRating() + ratingBar2.getStepSize());
                ratingBar3.setRating(ratingBar3.getRating() + ratingBar3.getStepSize());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar1.setRating(ratingBar1.getRating() - ratingBar1.getStepSize());
                //default 0.5 stepsize 1이면 한개씩
                ratingBar2.setRating(ratingBar2.getRating() - ratingBar2.getStepSize());
                ratingBar3.setRating(ratingBar3.getRating() - ratingBar3.getStepSize());
            }
        });

    }
}
