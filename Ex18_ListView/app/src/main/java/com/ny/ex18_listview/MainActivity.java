package com.ny.ex18_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText1;
    Button button1, button2;
    ListView listView1;
    ArrayList<String> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01);

        editText1 = findViewById(R.id.editText1);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        listView1 = findViewById(R.id.listView1);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, list);
        listView1.setAdapter(adapter);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(editText1.getText().toString());
                editText1.setText("");
                adapter.notifyDataSetChanged();
                //어댑터의 변경을 시스템에게 알려 바로 새로고침하는 것
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               list.remove(editText1.getText().toString());
                editText1.setText("");
                adapter.notifyDataSetChanged();
                //어댑터의 변경을 시스템에게 알려 바로 새로고침하는 것
            }
        });

    }
}
