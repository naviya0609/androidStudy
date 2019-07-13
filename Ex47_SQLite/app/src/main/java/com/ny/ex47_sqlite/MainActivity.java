package com.ny.ex47_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name, tel, addr,result;
    Button button1,button2,button3,button4,button5;
    MyDB myDB = new MyDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        tel = findViewById(R.id.tel);
        addr = findViewById(R.id.addr);
        result = findViewById(R.id.result);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        button1.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db = myDB.getWritableDatabase(); //읽고 쓰기 가능.
                    String sql = "insert into phonelist values ( null, ?, ?, ? )"; //동적바인딩 변수 ?,?,? prepared statement

                    //동적바인딩 변수들 안드로이드는 무조건 배열로 만들어서 넘김. api에 정의된 방식
                    String[] arr = { name.getText().toString(), tel.getText().toString(), addr.getText().toString() };

                    //db 실행
                    db.execSQL(sql,arr);

                    myDB.close();
                    name.setText("");
                    tel.setText("");
                    addr.setText("");

                   result.setText("insert ok");
                }
            }
        );
        button2.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //MyDB 에서 생성한 db 사용 읽고 쓰기 가능하게 만드는 메소드
                    SQLiteDatabase db = myDB.getWritableDatabase();
                    String sql = "select * from phonelist";

                    //select문은 커서를 이용해서 받은 정보 출력, 인자 넘길때 하나라도 배열로 넘김 string[]
                    Cursor cursor = db.rawQuery(sql,null);
                    //결과 cursor가 들고있음 >> 저장하려고 stringbuffer
                    //string 가변 아니므로 추가할 때는 stringbuffer! append!!!!
                    StringBuffer buffer = new StringBuffer();
                    while(cursor.moveToNext()){ //칼럼인덱스로만 getter 값으로 못가져옴.
                        int num = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String tel  = cursor.getString(2);
                        String addr = cursor.getString(3);
                        buffer.append(num+","+name+", "+tel+", "+addr+"\r\n");
                    }
                    result.setText(buffer.toString());
                    cursor.close();
                    myDB.close();
                }
            }
        );
        //이름으로 찾기
        button3.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db = myDB.getWritableDatabase();
                    String sql = "select * from phonelist where name = ? ";
                    String[] arr= {name.getText().toString()};
                    Cursor cursor =  db.rawQuery(sql,arr);

                    while(cursor.moveToNext()){
                        name.setText(cursor.getString(1)); //0부터 시작하는 위치값만 가져옴
                        tel.setText(cursor.getString(2));
                        addr.setText(cursor.getString(3));
                    }

                    result.setText("search ok");
                    myDB.close();
                    cursor.close();

                }
            }
        );
        button4.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db= myDB.getWritableDatabase();
                    String sql = "update phonelist set tel = ? , addr = ? where name = ?";
                    String[] arr= {tel.getText().toString(),addr.getText().toString(), name.getText().toString()};
                    db.execSQL(sql,arr);
                    myDB.close();
                    name.setText("");
                    tel.setText("");
                    addr.setText("");
                }
            }
        );
        button5.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db= myDB.getWritableDatabase();
                    String sql = "delete from phonelist where name = ?";
                    String[] arr= { name.getText().toString()};
                    db.execSQL(sql,arr);
                    myDB.close();
                    name.setText("");
                    result.setText("delete ok");
                }
            }
        );
    }
}
