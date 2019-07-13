package com.ny.ex06_calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText1, editText2,editText3 ;
    Button btn1 ;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        btn1 = findViewById(R.id.btn1);
        result = findViewById(R.id.result);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int su1 = Integer.parseInt(editText1.getText().toString());
                int su2 = Integer.parseInt(editText2.getText().toString());
                String op = editText3.getText().toString();

                int res = 0 ;
                switch (op){
                    case "+" : res = su1 + su2 ;  break;
                    case "-" : res = su1 - su2 ;  break;
                    case "*" : res = su1 * su2 ;  break;
                    case "/" : res = su1 / su2 ;  break;
                }
                result.setText(String.valueOf(res));
            }
        });
    }
}
