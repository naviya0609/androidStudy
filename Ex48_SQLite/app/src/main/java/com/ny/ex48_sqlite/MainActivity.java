package com.ny.ex48_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker datePicker;
    EditText editText;
    Button button;
    String dairyDate; //날짜저장변수
    //db 생성
    MyDB myDB = new MyDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        //오늘날짜 구하기
        int mYear = Calendar.getInstance().get(Calendar.YEAR);
        final int mMonth = Calendar.getInstance().get(Calendar.MONTH);
        final int mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        dairyDate = mYear + "_" + (mMonth +1) +"_" + mDay;

        //db에 해당날짜 스케줄 파악하기, 스케줄 내용 리턴받기
        String msg = readDiary(dairyDate);

        //데이터 피커 변경되면 날짜감지, 해당날짜의 정보가 있는지 판별
        datePicker.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dairyDate = year + "_" + (monthOfYear +1) +"_" +dayOfMonth ;
                String msg = readDiary(dairyDate);
                editText.setText(msg);
                Toast.makeText(MainActivity.this, dairyDate+msg, Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(button.getText().toString().equals("스케줄 저장")){
                       SQLiteDatabase db = myDB.getWritableDatabase();
                       String sql = "insert into mydb values (?,?)";
                       String[] arr = {dairyDate , editText.getText().toString().trim()};
                       db.execSQL(sql,arr);
                       myDB.close();
                        Toast.makeText(MainActivity.this, "저장 성공", Toast.LENGTH_SHORT).show();
                    } else if (button.getText().toString().equals("스케줄 수정")){
                        SQLiteDatabase db = myDB.getWritableDatabase();
                        String sql = "update mydb set content = ? where dairyDate = ?";
                        String[] arr = {editText.getText().toString().trim(),dairyDate};
                        db.execSQL(sql,arr);
                        myDB.close();
                        Toast.makeText(MainActivity.this, "수정 성공", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    //날짜받아서 db조회 스케줄 체크
    public String readDiary(String dairyDate) {
        String result = null;
        SQLiteDatabase db =myDB.getWritableDatabase();
        String sql = "select * from mydb where dairyDate = ? ";
        String[] arr = {dairyDate};
        Cursor cursor = db.rawQuery(sql, arr);

        //해당날짜에 자료가 있는지 판별
        if (cursor.moveToFirst()){
            result = cursor.getString(1);
            button.setText("스케줄 수정");
        } else {
            editText.setHint("저장정보 없음");
            button.setText("스케줄 저장");
        }
        cursor.close();
        myDB.close();

        return result;
    }

}
