package com.ny.ex49_sqlite;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.widget.Toast.*;


public class MyView03 extends AppCompatActivity {
    TextView textView1;
    EditText eResult;
    Button button7, button8, button9;
    DAO dao;
    String nalja ;
    String idx ; // 수정, 삭제 할때 사용하는 변수

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view03);

        textView1 = findViewById(R.id.textView1);
        eResult = findViewById(R.id.eResult);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        // intent를 통해서 들어온 날짜 받기
        Intent re_intent = getIntent();
        nalja = re_intent.getStringExtra("nalja");
        // Toast.makeText(this, nalja, Toast.LENGTH_SHORT).show();
        textView1.setText(nalja+ " 일정");

        // 해당 날짜에 일정이 있으면 표시 , 없으면 Toast
        dao = DAO.db_open(this);
        final Cursor cursor = dao.selectData(nalja);
        if(cursor.moveToFirst()){
            button7.setText("수정하기");
            idx = cursor.getString(0);
            eResult.setText(cursor.getString(2));
        }else{
            button7.setText("저장하기");
            Toast.makeText(this, "스케줄이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
        }


        button7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(button7.getText().toString().equals("수정하기")){
                    // update
                    dao.updateData(idx, eResult.getText().toString().trim());
                    Toast.makeText(MyView03.this, "수정 성공", Toast.LENGTH_SHORT).show();
                }else if(button7.getText().toString().equals("저장하기")){
                    // insert
                    dao.insertData(nalja,eResult.getText().toString().trim());
                    Toast.makeText(MyView03.this, "저장 성공", Toast.LENGTH_SHORT).show();

                    Cursor cursor = dao.selectData(nalja);
                    if(cursor.moveToFirst()){
                        idx = cursor.getString(0);
                    }

                    button7.setText("수정하기");
                }
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] c_nalja = nalja.split("_");
                int y = Integer.parseInt(c_nalja[0]);
                int m = Integer.parseInt(c_nalja[1])-1;
                int d = Integer.parseInt(c_nalja[2]);

                new DatePickerDialog(MyView03.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        nalja = year+"_"+(month+1)+"_"+day ;
                        textView1.setText(nalja+" 일정");
                        eResult.setText("");

                        Cursor re_cursor = dao.selectData(nalja);
                        if(re_cursor.moveToFirst()){
                            button7.setText("수정하기");
                            idx = re_cursor.getString(0);
                            eResult.setText(re_cursor.getString(2));
                        }else{
                            button7.setText("저장하기");
                            Toast.makeText(MyView03.this, "스케줄이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },y,m,d).show();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.deleteData(idx);
                Toast.makeText(MyView03.this, "삭제 성공", Toast.LENGTH_SHORT).show();
                eResult.setText("");
                button7.setText("저장하기");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
