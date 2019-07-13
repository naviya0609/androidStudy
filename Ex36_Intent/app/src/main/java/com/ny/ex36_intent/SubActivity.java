package com.ny.ex36_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity  extends AppCompatActivity {
    EditText editText1;
    Button button2, button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);

        editText1 = findViewById(R.id.editText1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        Intent rintent = getIntent();
        String msg = rintent.getStringExtra("msg");
        editText1.setText(msg);

        final Intent mintent = new Intent();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mintent.putExtra("result",editText1.getText().toString());
              setResult(RESULT_OK,mintent);
              finish();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED,mintent);
                finish();
            }
        });


    }
}
