package com.ny.ex69_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText editText;
Button button;
TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        button=findViewById(R.id.button);
        result=findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg= editText.getText().toString().trim();
                //서비스에 정보를 보낼때도 Intent를 사용한다.
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("command","show");
                intent.putExtra("msg",msg);

                //서비스를 시작하는 목적과 Intent를 동시에 전달할때 사용하는 메소드
                startService(intent);
                editText.setText("");//전송후 초기화
            }
        });
    }

    // 서비스에서 넘어온 Intent 받기
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showMsg(intent);
    }
    private void showMsg(Intent intent){
        if(intent != null){
            String msg = intent.getStringExtra("msg");
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
