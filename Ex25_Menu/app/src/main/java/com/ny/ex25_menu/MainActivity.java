package com.ny.ex25_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 메뉴
    // 1. 옵션 : 주메뉴, 메뉴버튼을 클릭하면 나오는 메뉴
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 옵션 메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(0,0,0,"짜장면");
        menu.add(0,1,0,"짬뽕");
        menu.add(0,2,0,"탕수육");
        SubMenu sm = menu.addSubMenu(0,3,0,"만두");
        sm.add(0,4,0,"물만두");
        sm.add(0,5,0,"군만두");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         Toast.makeText(this, item.getTitle() + "선택", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
