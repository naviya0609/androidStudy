package com.ny.ex49_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


// DB 처리하는 클래스
public class DAO {
    Context context;
    MyDB mydb ;
    SQLiteDatabase db;

    public DAO() {   }
    public DAO(Context context) {
        this.context = context;
        mydb = new MyDB(context);
        db = mydb.getWritableDatabase();
    }

    public static DAO db_open(Context context) throws SQLException{
        DAO dao = new DAO(context);
        return dao ;
    }
    public void db_close(){
        db.close();
    }

    // select 날짜 받아서 DB에 해당 날짜 존재 여부 체크하는 메소드
    public boolean chkNalja(String nalja){
        String sql = "select * from myplanning where nalja = ? ";
        String[] arr = {nalja};
        Cursor cursor = db.rawQuery(sql,arr);
        return cursor.moveToFirst();
    }
    // insert 날짜와 내용을 받아서 DB에 저장하는 메소드
    public void insertData(String nalja, String planning){
        String sql = "insert into myplanning values(null, ?, ?)" ;
        String[] arr = {nalja, planning};
        db.execSQL(sql, arr);
    }

    // select 날짜를 받아서 내용(cursor)을 내보내는 메소드
    public Cursor selectData(String nalja){
        String sql = "select * from myplanning where nalja = ? ";
        String[] arr = {nalja};
        return  db.rawQuery(sql,arr);
    }
    // update
    public void updateData(String idx, String planning){
        String sql = "update myplanning set planning = ? where idx = ?" ;
        String[] arr = {planning, idx};
        db.execSQL(sql,arr);
    }
    // delete
    public void deleteData(String idx){
        String sql = "delete from myplanning where idx = ?";
        String[] arr = {idx};
        db.execSQL(sql,arr);
    }
    // select_asc
    public Cursor selectAsc(){
        String sql = "select * from myplanning order by nalja asc";
        return db.rawQuery(sql, null);
    }
    // select_desc
    public Cursor selectDesc(){
        String sql = "select * from myplanning order by nalja Desc";
        return db.rawQuery(sql, null);
    }

}
