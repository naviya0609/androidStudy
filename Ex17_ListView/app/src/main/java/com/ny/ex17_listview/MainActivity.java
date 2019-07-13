package com.ny.ex17_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //둘다 어댑터를 사용한다.
    //리스트뷰 : 이벤트처리 setOnItemClickListener 사용, ArrayList 사용
    //스피너   : 이벤트처리 setOnItemSelectedListener 사용, Array 사용

    ListView listView1;
    ArrayList<String> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);

        setContentView(R.layout.view01);
        listView1 =findViewById(R.id.listView1);

        list.add("나라선택"); //화면 길어지면 자동 스크롤 처리됨. 스크롤뷰 안넣어도됨
       for(int i=0; i<10; i++) {
            list.add("한국");
            list.add("미국");
            list.add("중국");
            list.add("영국");
       }

        /*android.R.layout.simple_list_item_1;
        android.R.layout.simple_list_item_2;
        android.R.layout.simple_list_item_activated_1;
        android.R.layout.simple_list_item_activated_2;
        android.R.layout.simple_list_item_checked;
        android.R.layout.simple_list_item_multiple_choice;
        android.R.layout.simple_list_item_single_choice;*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = (String)listView1.getAdapter().getItem(position);
                Toast.makeText(MainActivity.this, " position : "+position +",  국가 : "+msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
