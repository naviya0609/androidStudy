package com.ny.ex60_sensor;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);

        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> list = manager.getSensorList(Sensor.TYPE_ALL);
        String msg = "전체 센서의 수 : " + list.size()+"\n";
        for(Sensor s : list){
            msg +=  "sensor_name : "        + s.getName()+"\n"           +       //이름
                    "sensor_power: "        + s.getPower()+"\n"          +       //단위
                    "sensor_resolution : " + s.getResolution()+"\n"     +       //해상도
                    "sensor_range : "       + s.getMaximumRange()+"\n"  ;        //최대범위

        }
        textView1.setText(msg);
    }
}
