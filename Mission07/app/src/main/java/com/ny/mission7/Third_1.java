package com.ny.mission7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Third_1 extends AppCompatActivity {
    TextView textView;
    Button button6, button7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_1);
        textView = findViewById(R.id.textView);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);

        Intent rintent = getIntent();
        String msg= rintent.getStringExtra("msg") ;
        Toast.makeText(this, "titleMsg : "+ msg, Toast.LENGTH_SHORT).show();

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rintent = new Intent();
                rintent.putExtra("str", "ok");
                setResult(RESULT_OK,rintent);
                finish();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rintent = new Intent();
                setResult(RESULT_CANCELED,rintent);
                finish();
            }
        });

    }
}
