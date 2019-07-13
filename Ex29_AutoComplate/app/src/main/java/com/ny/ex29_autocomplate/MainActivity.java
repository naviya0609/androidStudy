package com.ny.ex29_autocomplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView1;
    Button button1;
    //String[] items= {"Reader","reader","Read","read","reply","red","references"};
    ArrayList<String> item = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView1 =findViewById(R.id.autoCompleteTextView1);
        button1 =findViewById(R.id.button1);
    /*    autoCompleteTextView1.setAdapter(
                new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,item)
        );
        */
        item.add("Reader");
        item.add("reader");
        item.add("Read");
        item.add("read");
        item.add("reply");
        item.add("red");
        item.add("references");

        autoCompleteTextView1.setAdapter(
                new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,item)
        );

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = autoCompleteTextView1.getText().toString();
                if(!item.contains("msg")) {
                    item.add(msg);
                    autoCompleteTextView1.setText("");
                }
                autoCompleteTextView1.setAdapter(
                        new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line,item)
                );
            }
        });



    }
}
