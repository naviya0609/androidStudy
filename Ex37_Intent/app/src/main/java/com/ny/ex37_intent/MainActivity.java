package com.ny.ex37_intent;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView[] imageViews = new ImageView[9];
    Integer[] img = {R.id.imageView1,R.id.imageView2,R.id.imageView3,
            R.id.imageView4,R.id.imageView5,R.id.imageView6,
            R.id.imageView7,R.id.imageView8,R.id.imageView9 };
    int[] count = new int[9]; // 점수 저장하는 변수

    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 그림을 선택하면 선택한 횟수를 count[]이 기억하고 있음음
        for (int i=0; i<img.length; i++){
            count[i] = 0;
            imageViews[i] = findViewById(img[i]);

            final int cnt = i ;
            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count[cnt]++;
                }
            });
        }

        // 투표하기 버튼
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("count", count);
                startActivityForResult(intent,1000);
            }
        });
    }

    // 되돌아 오면 초기화를 하자
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            for (int i=0; i<count.length; i++){
                count[i] = 0 ;
            }
        }
    }
}
