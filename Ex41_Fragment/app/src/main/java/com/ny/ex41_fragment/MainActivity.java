package com.ny.ex41_fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallback{
    ListFragment listFragment;
    ViewFragment viewFragment;
    int[] images = { R.drawable.dream01,R.drawable.dream02,R.drawable.dream03 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);
        viewFragment = (ViewFragment) manager.findFragmentById(R.id.viewFragment);
    }

    @Override
    public void onImageSelected(int position) {
        viewFragment.setImage(images[position]);
    }
}
