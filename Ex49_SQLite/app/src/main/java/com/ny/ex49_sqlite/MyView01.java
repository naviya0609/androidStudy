package com.ny.ex49_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Calendar;

public class MyView01 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01);

        // 오늘 일정 추가
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 오늘날짜를 구하지 않고 그냥 넘기기
                Intent intent = new Intent(MyView01.this, MyView02.class);
                startActivity(intent);
            }
        });

        // 일정보기
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 오늘날짜를 구해서 넘기기
                // 오늘날짜 구하기
                int y = Calendar.getInstance().get(Calendar.YEAR);
                int m = Calendar.getInstance().get(Calendar.MONTH)+1;
                int d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                String nalja = y+"_"+m+"_"+d;
                Intent intent = new Intent(MyView01.this, MyView03.class);
                intent.putExtra("nalja", nalja);
                startActivity(intent);
            }
        });

        // 전체일정
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyView01.this, MyView04.class);
                startActivity(intent);
            }
        });
    }
}
