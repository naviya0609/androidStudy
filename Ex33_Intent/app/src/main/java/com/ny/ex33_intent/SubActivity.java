package com.ny.ex33_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    Button button2;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);
        button2 = findViewById(R.id.button2);
        textView1 = findViewById(R.id.textView1);

        //넘어온 intent 정보 받기
        Intent rintent = getIntent();

       /* String name= rintent.getStringExtra("name");
        int age = rintent.getIntExtra("age",0);
        Boolean gender= rintent.getBooleanExtra("gender",false);
        textView1.setText("이름 : " + name +" 나이 : "+age + " 성별 : "+ gender );
        */

        String[] items = rintent.getStringArrayExtra("item");
        StringBuffer sb= new StringBuffer();
        for(String k : items){
            sb.append(k+"\n");
        }
       textView1.setText(sb.toString());

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        });
    }
}
