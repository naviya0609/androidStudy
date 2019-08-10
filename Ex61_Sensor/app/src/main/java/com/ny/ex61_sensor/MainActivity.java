package com.ny.ex61_sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView textView1, textView2, textView3, textView4, textView5, textView6 ;
    Sensor s1, s2, s3, s4, s5, s6 ;
    SensorManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);

        // 센서 메니져을 통해서 센서를 구하자
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        s1 = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);   // 가속도
        s2 = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);          // 중력
        s3 = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);        // 회전
        s4 = manager.getDefaultSensor(Sensor.TYPE_LIGHT);             // 밝기
        s5 = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);  // 자력
        s6 = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);        // 근접
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 이벤트 리스너 달기
        manager.registerListener(this,s1, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,s2, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,s3, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,s4, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,s5, SensorManager.SENSOR_DELAY_NORMAL);
        //내부클래스
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float v1 = event.values[0];  // x축 (방위축)
                float v2 = event.values[0];  // y축 (피치축)
                float v3 = event.values[0];  // z축 (롤축)
                textView5.setText("x="+v1 +"y="+v2+"z="+v3);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {  }
        }, s5, SensorManager.SENSOR_DELAY_UI);

        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float v1 = event.values[0];  // x축 (방위축)
                float v2 = event.values[1];  // y축 (피치축)
                float v3 = event.values[2];  // z축 (롤축)
                textView6.setText("x="+v1 +"y="+v2+"z="+v3);
                if(v1 == 0){
                    Toast.makeText(MainActivity.this, "접근완료", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "접근해제", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) { }
        }, s6, SensorManager.SENSOR_DELAY_UI);

    }
    @Override
    public void  onSensorChanged(SensorEvent event) {
        // 이벤트 처리
        // 동기화 처리 하자
        synchronized (this){
            // 방향센서 3개 넘어온다.
            float v1 = event.values[0];  // x축 (방위축)
            float v2 = event.values[1];  // y축 (피치축)
            float v3 = event.values[2];  // z축 (롤축)

            switch (event.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER :
                    textView1.setText("x="+v1 +"y="+v2+"z="+v3);break;
                case Sensor.TYPE_GRAVITY :
                    textView2.setText("x="+v1 +"y="+v2+"z="+v3);break;
                case Sensor.TYPE_GYROSCOPE :
                    textView3.setText("x="+v1 +"y="+v2+"z="+v3);break;
                case Sensor.TYPE_LIGHT:
                    textView4.setText("x="+v1 +"y="+v2+"z="+v3);break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    textView5.setText("x="+v1 +"y="+v2+"z="+v3);break;
               /* case Sensor.TYPE_PROXIMITY :
                    textView6.setText("x="+v1 +"y="+v2+"z="+v3);

                    if(v1 == 0){
                        Toast.makeText(this, "접근완료", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "접근해제", Toast.LENGTH_SHORT).show();
                    }
                    break;*/
            }
        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        manager.unregisterListener(this);
    }
}
