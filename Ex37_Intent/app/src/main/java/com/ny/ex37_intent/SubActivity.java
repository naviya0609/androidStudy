package com.ny.ex37_intent;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class SubActivity extends AppCompatActivity {
    int[] count ;
    RatingBar[] ratingBars = new RatingBar[9];
    int[] rantId = {R.id.ratingBar1, R.id.ratingBar2, R.id.ratingBar3,
            R.id.ratingBar4, R.id.ratingBar5, R.id.ratingBar6,
            R.id.ratingBar7, R.id.ratingBar8, R.id.ratingBar9 };
    Button button2, button3 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        Intent r_intent = getIntent();
        count = r_intent.getIntArrayExtra("count");

        for (int i=0; i <rantId.length; i++){
            ratingBars[i] = findViewById(rantId[i]);
            // 별 세팅
            ratingBars[i].setRating(count[i]);
        }

        // 되돌아 가기
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 최다 득표 그림 보기
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t_intent = new Intent(SubActivity.this, ThirdActivity.class);
                int max = 0;
                int index = 0 ;
                for(int i=0; i< count.length; i++){
                    if(max < count[i]){
                        max = count[i];
                        index = i ;
                    }
                }
                t_intent.putExtra("index", index);
                startActivity(t_intent);
            }
        });
    }
}
