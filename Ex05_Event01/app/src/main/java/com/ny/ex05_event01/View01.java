package com.ny.ex05_event01;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// 액티비티는 반드시 Activity클래스를 상속받아야 한다.
public class View01  extends AppCompatActivity implements View.OnClickListener{
    TextView tv1, tv2 ;
    Button btn1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // xml 화면 지정하는 메소드
        setContentView(R.layout.view01);

        // 이벤트 소스 : 이벤트가 발생하는 뷰
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        btn1 = findViewById(R.id.btn1);


        // 이벤트 리스너 : 이벤트를 감지하고 실제 일처리하는 클래스(OnClickListener)지정
        tv1.setOnClickListener(this);

        // 익명 내부타입
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setTextColor(Color.MAGENTA);
                tv1.setText("nojm73@naver.com");
            }
        });

        btn1.setOnClickListener(new innerEvent());
    }
    // OnClickListener의 추상메소드
    @Override
    public void onClick(View view) {
        tv2.setText("이제아카데미 노종문");
        tv2.setTextSize(50);
        tv2.setTextColor(Color.YELLOW);
    }

    // 내부 클래스를 만들어서 이벤트 처리
    class innerEvent implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Toast.makeText(View01.this, "버튼을 누르셨네요", Toast.LENGTH_SHORT).show();

        }
    }

}
