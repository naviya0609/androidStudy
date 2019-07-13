package com.ny.ex50_location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
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
                showLocationService();
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showLocationService(){
        // location은 locationmanager를 이용해서 서비스를 받는다.
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        // 퍼미션 체크
        if(ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            return ;
        }

        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                // LocationManager.NETWORK_PROVIDER,
                10000,
                0,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        // 위치확인
                        Double latitude = location.getLatitude();    // 위도
                        Double longitude = location.getLongitude();  // 경도

                        textView.setText("내위치 : " + latitude+", "+longitude+"\n"+
                                getAddress(latitude, longitude));
                    }
                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {}
                    @Override
                    public void onProviderEnabled(String s) { }
                    @Override
                    public void onProviderDisabled(String s) {}
                }


        );

    }
    public String getAddress(double latitude, double longitude){
        String address = null;
        // 구글에서 제공한 주소 얻어오기
        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
        List<Address> list = null;
        try{
            list = geocoder.getFromLocation(latitude, longitude,1);
            if(list.size()>0){
                Address addr = list.get(0);
                address = addr.getCountryName()+"\n" +
                        addr.getLocality()+"\n" +
                        addr.getThoroughfare()+"\n"+
                        addr.getFeatureName();
            }
        }catch (Exception e){

        }
        return address;
    }

}
