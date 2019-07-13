package com.ny.ex47_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//안드로이드 내장 db SQLite는 SQLiteOpenHelper 상속
//추가메소드2개(onCreate,onUpgrade) 기본생성자 없으므로 context 받는 생성자 생성
public class MyDB extends SQLiteOpenHelper {

    public MyDB(Context context) {
        // name : "DB이름",
        // factory:커서를 저장하는 매개변수 (디폴트:null), DB버전)
        super(context, "phonelist", null, 1);
    }

    //db이름으로 존재하는 것이 없으면 oncreate호출됨// 이름같고 버전다르면 upgrade 호출
    @Override
    public void onCreate(SQLiteDatabase db) {
        //테이블 생성
        String sql = "create table phonelist(" +
                "num integer primary key autoincrement, " +
                "name text , tel text, addr text) ";
        db.execSQL(sql);

        sql = "insert into phonelist values(null,'mary', '010-1212-1212','서울시 서초구 서초동')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table phonelist";
        db.execSQL(sql);
        onCreate(db); //삭제후 재생성
    }
}
