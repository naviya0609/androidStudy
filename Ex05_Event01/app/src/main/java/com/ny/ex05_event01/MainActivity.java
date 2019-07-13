package com.ny.ex05_event01;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView1,textView2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // layout 화면 지정하는 메소드
        setContentView(R.layout.view01);

        // xml에 등록된 뷰을 찾아서 인식 시키자 => findViewById()
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        // getter => 정보가져오기
        String msg = textView1.getText().toString();
        msg += ", " + textView1.getTextSize();

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        // setter => 데이터 설정정
       textView2.setText("대한민국 KOREA");
        textView2.setTextColor(20);
        textView2.setTextColor(Color.GREEN);
    }
}
