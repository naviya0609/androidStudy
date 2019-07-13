package com.ny.ex49_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper {
    public MyDB(Context context) {
        super(context, "myplanning", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table myplanning("+
                "idx integer primary key autoincrement," +
                "nalja text not null, planning text not null)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql ="drop table myplanning";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}

