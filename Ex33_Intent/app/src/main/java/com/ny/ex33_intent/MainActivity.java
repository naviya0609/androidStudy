package com.ny.ex33_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);

                //정보저장 데이터 담아서 넘김
                intent.putExtra("name","홍기동");
                intent.putExtra("age",25);
                intent.putExtra("gender",false);

                String[] items = {"김주은","18","서울"};
                intent.putExtra("item",items);

                startActivity(intent);
            }
        });
    }
}
