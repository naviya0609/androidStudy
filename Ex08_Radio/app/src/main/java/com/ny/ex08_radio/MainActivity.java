package com.ny.ex08_radio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;
    RadioGroup rg1 ;
    Button btn1;
    TextView result;
    String op ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        rg1 = findViewById(R.id.rg1);
        btn1 = findViewById(R.id.btn1);
        result = findViewById(R.id.result);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb1 : op ="+" ;     break;
                    case R.id.rb2 : op ="-" ;     break;
                    case R.id.rb3 : op ="*" ;     break;
                    case R.id.rb4 : op ="/" ;     break;
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int su1 = Integer.parseInt(et1.getText().toString());
                    int su2 = Integer.parseInt(et2.getText().toString());
                    int res = 0 ;
                    switch (op){
                        case "+" : res = su1 + su2 ; break;
                        case "-" : res = su1 - su2 ; break;
                        case "*" : res = su1 * su2 ; break;
                        case "/" : res = su1 / su2 ; break;
                    }

                    result.setText(String.valueOf(res));
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "제대로 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
