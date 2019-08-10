package com.ny.ex70_service;


import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView result;
    Button button;

    MyReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        result = findViewById(R.id.result);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editText.getText().toString().trim();

                Intent intent = new Intent(getApplicationContext(),MyService.class);
                intent.putExtra("msg",msg);

                startService(intent);
                editText.setText("");
            }
        });
        // 내부 클래스로 MyReceiver를 만들자
        receiver = new MyReceiver();
        // Intent 필터를 이용해서 넘어온 인텐트 정보를 알 수 있다.
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ict.service02");
        registerReceiver(receiver,filter);
    }

    // 서비스에서 넘어온 인텐트를 받아서 처리
    // 외부클래스로 클래스 생성시 activicity 반드시 추가해야함.
    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            processIntent(intent);
        }
    }

    private void processIntent(Intent intent){
        String msg = intent.getStringExtra("msg");
        result.setText("받은 결과 : " + msg);

        Intent stop_Service = new Intent(getApplicationContext(),MyService.class);
        stopService(stop_Service);
    }
    // 액티비티가 끝나면 브로드캐스트 리시버도 끝내자
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
