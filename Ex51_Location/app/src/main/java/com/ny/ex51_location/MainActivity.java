package com.ny.ex51_location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationService();
            }
        });
        //  권한 자동 부여
        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    public void startLocationService(){
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            return ;
        }
        try{
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String msg = "최근위치위치 >> latitude : "+ latitude +" \n최근위치 >> longitude : " +longitude;
            textView.setText(msg);

            GPSListener gpsListener = new GPSListener();
            long minTime = 1000;    //최소시간
            float minDistance = 0;  //최소이동거리
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime,minDistance, gpsListener);
        }catch (Exception e){
        }
    }

    class GPSListener implements LocationListener{
        public void onLocationChanged(Location location){
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String msg = "내위치 >> latitude : "+ latitude +" \n내위치 >> longitude : " +longitude;
            textView.setText(msg);
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {        }
        @Override
        public void onProviderEnabled(String s) {   }
        @Override
        public void onProviderDisabled(String s) {    }
    }

    //권한 자동부여
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this,requestCode, permissions,this);
    }

    @Override
    public void onDenied(int i, String[] strings) {}
    @Override
    public void onGranted(int i, String[] strings) { }
}
