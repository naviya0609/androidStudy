package com.ny.ex40_fragmetn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MainFragment mainFragment;
    SubFragment subFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        subFragment = new SubFragment();
    }

    public void onFragmentChaged(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,subFragment).commit();
    }
}
