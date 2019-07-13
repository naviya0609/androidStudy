package com.ny.ex34_intent;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button1 = findViewById(R.id.button1);
        textView1 = findViewById(R.id.texView1);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("su1",100);
                intent.putExtra("su2",0);
                //startActivity(intent); 단방향
                startActivityForResult(intent, 1000);

            }
        });
    }

    // startActivityForResult  사용에 수반되는 메소드
    // retrun 결과 받는 메소드

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Intent data 서브에서 보낸정보 갖는 인텐트객체
        if (requestCode == 1000){
            if(resultCode ==RESULT_OK){
                textView1.setText("성공 : "+data.getIntExtra("res",0));
            }else if (resultCode==RESULT_CANCELED){
                textView1.setText("실패 : "+data.getStringExtra("res"));
            }
        }
    }
}
