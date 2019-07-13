package com.ny.ex17_listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class View02 extends AppCompatActivity {
    ListView listView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view02);
        listView2.findViewById(R.id.listView2);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = (String)listView2.getAdapter().getItem(position);
                Toast.makeText(View02.this, "위치 : "+position+" , 대상 : "+msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
