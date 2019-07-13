package com.ny.ex19_listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class View01 extends AppCompatActivity {
    ListView listView1;
    UserAdapter adapter;
    ArrayList<VO> list; //vo 여러개

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01);
        listView1 = findViewById(R.id.listView1);
        list = new ArrayList<>();
        list.add(new VO(R.drawable.pic1,"BABY 1"));
        list.add(new VO(R.drawable.pic2,"BABY 2"));
        list.add(new VO(R.drawable.pic3,"BABY 3"));
        list.add(new VO(R.drawable.pic4,"BABY 4"));
        list.add(new VO(R.drawable.pic5,"BABY 5"));
        list.add(new VO(R.drawable.pic6,"BABY 6"));

        // 안드로이드에서 지원하는 어댑터가 아니라 사용자 양식으로 만든 어댑터를 생성하자
        adapter = new UserAdapter(this, R.layout.user_item, list);
        listView1.setAdapter(adapter);

    }
}
