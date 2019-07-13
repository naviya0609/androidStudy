package com.ny.mission7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Third_3 extends AppCompatActivity {
    TextView textView;
    Button button10, button11;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_3);
        textView = findViewById(R.id.textView);
        button10 = findViewById(R.id.button8);
        button11 = findViewById(R.id.button9);

        Intent rintent = getIntent();
        String msg= rintent.getStringExtra("msg") ;
        Toast.makeText(this, "titleMsg : "+ msg, Toast.LENGTH_SHORT).show();


        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rintent = new Intent();
                rintent.putExtra("str", "ok");
                setResult(RESULT_OK,rintent);
                finish();
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rintent = new Intent();
                setResult(RESULT_CANCELED,rintent);
                finish();
            }
        });

    }
}
