package com.ny.ex52_location;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    double latitude, longitude ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 자동권한 부여
        AutoPermissions.Companion.loadAllPermissions(this,101);

        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        try{
            if(ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                return;
            }
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            latitude = location.getLatitude();
            longitude = location.getLongitude();

            // 묵시적 인텐트를 사용하여 지동 위치 표시
            Uri uri = Uri.parse("geo:"+latitude+","+location+"?z=20");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,uri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

            GPSListener gpsListener = new GPSListener();
            long minTime = 10000 ;
            float minDistance = 0 ;
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,minDistance,gpsListener);

        }catch (Exception e){
        }
    }

    class GPSListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) { }
        @Override
        public void onProviderEnabled(String s) {}
        @Override
        public void onProviderDisabled(String s) {}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion
                .parsePermissions(this,requestCode,permissions,this);
    }

    @Override
    public void onDenied(int i, String[] strings) {}
    @Override
    public void onGranted(int i, String[] strings) {}
}


/* 자동권한부여받기- 꺼내쓰는것으로...
public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //자동권한부여
        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }

    @Override
    public void onDenied(int i, String[] strings) { }

    @Override
    public void onGranted(int i, String[] strings) { }
}
*/