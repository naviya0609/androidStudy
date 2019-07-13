package com.ny.ex49_sqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MyView02 extends AppCompatActivity {
    EditText eYear, eMonth, eDay, ePlay ;
    Button button5, button6 ;
    String nalja;
    DAO dao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view02);

        eYear = findViewById(R.id.eYear);
        eMonth = findViewById(R.id.eMonth);
        eDay = findViewById(R.id.eDay);
        ePlay = findViewById(R.id.ePlay);

        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        // 오늘날짜 구하기
        int y = Calendar.getInstance().get(Calendar.YEAR);
        int m = Calendar.getInstance().get(Calendar.MONTH)+1;
        int d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        eYear.setText(String.valueOf(y));
        eMonth.setText(String.valueOf(m));
        eDay.setText(String.valueOf(d));

        nalja = y+"_"+m+"_"+d;

        dao = DAO.db_open(MyView02.this);
        Cursor cursor = dao.selectData(nalja);
        if(cursor.moveToFirst()){
            // Toast.makeText(this, "자료있음", Toast.LENGTH_SHORT).show();
            ePlay.setText(cursor.getString(2));
            ePlay.setEnabled(false);
        }else{
            // Toast.makeText(this, "자료없음", Toast.LENGTH_SHORT).show();
            ePlay.setEnabled(true);
            Toast.makeText(this, "스케줄이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
        }

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao = DAO.db_open(MyView02.this);
                dao.insertData(nalja, ePlay.getText().toString().trim());
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
