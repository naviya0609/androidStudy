package com.ny.mission04;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class View01 extends AppCompatActivity {
    EditText inputText;
    TextView inputCount;
    Button button1,button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01);

        inputText.findViewById(R.id.inputText);
        inputCount.findViewById(R.id.inputCount);
        button1.findViewById(R.id.button1);
        button2.findViewById(R.id.button2);

        //edit text 이벤트 키업? addTextChangedListener/TextWatcher
        inputText.addTextChangedListener(new TextWatcher() {

            //변경전
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            //변경후
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                byte[] bytes= null;
                try{
                    //edittext 글자를 바이트 배열로 받음
                    bytes = s.toString().getBytes("utf-8");
                    //바이트 길이 반환
                     int strCount = bytes.length;
                     inputCount.setText( strCount + " / 80 Byte");
                }
                catch (Exception e){

                }
            }
            //변경후
            @Override
            public void afterTextChanged(Editable s) {
                String str  = s.toString();
                try {
                    byte[] bytes = str.getBytes("utf-8");
                    if (bytes.length>80){
                        s.delete(s.length()-2,s.length()-1);
                    }
                }catch (Exception e){

                }

            }
        });


        //전송
        button1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String message = inputText.getText().toString();
                  Toast.makeText(View01.this, message, Toast.LENGTH_SHORT).show();
              }
        });

        //닫기
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });

    }
}
