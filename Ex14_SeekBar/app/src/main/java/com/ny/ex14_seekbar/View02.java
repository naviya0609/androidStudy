package com.ny.ex14_seekbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

public class View02 extends AppCompatActivity {
    TextView textView2;
    SeekBar seekBar2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view02);

        textView2  = findViewById(R.id.textView2);
        seekBar2  = findViewById(R.id.seekBar2);

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                textView2.setText("변경값 : "+i+" %");
                setBrightness(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }
    private void setBrightness(int v){
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float) v / 100;
        getWindow().setAttributes(params);
    }
}
