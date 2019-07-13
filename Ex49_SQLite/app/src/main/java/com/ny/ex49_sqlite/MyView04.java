package com.ny.ex49_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MyView04 extends AppCompatActivity {
    RadioGroup rg;
    Button button10;
    TextView count;
    ListView list ;
    DAO dao;
    Cursor cursor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view04);
        rg = findViewById(R.id.rg);
        button10 = findViewById(R.id.button10);
        count = findViewById(R.id.count);
        list = findViewById(R.id.list);

        // 초기값
        rg.check(R.id.desc);
        dao = DAO.db_open(this);
        cursor = dao.selectDesc();
        disp();
        count.setText("전체건수 : " + cursor.getCount());


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.asc){
                    cursor = dao.selectAsc();
                    disp();
                }else if(i==R.id.desc){
                    cursor = dao.selectDesc();
                    disp();
                }
            }
        });


        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // 화면에 cursor 내용 뿌리기
    public void disp(){
        String[] arr = new String[cursor.getCount()];
        int count = 0 ;
        while(cursor.moveToNext()){
            String nalja = cursor.getString(1);
            String planning = cursor.getString(2);

            arr[count++] = nalja+" ☞ "+planning;
        }

        // 리스트 어댑터 생성
        final ListAdapter adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,arr  );
        // 리스트에 어댑터 장착
        list.setAdapter(adapter);

        // 리스트 Longclick 이벤트 >> 일정보기로 넘어가기 (날짜 넘어가야함)
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String[] nalja = parent.getAdapter().getItem(position).toString().split("☞");
                Intent intent = new Intent(MyView04.this, MyView03.class);
                intent.putExtra("nalja", nalja[0].trim());
                startActivity(intent);
                finish();
                return false;
            }
        });
    }
}
