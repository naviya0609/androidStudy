package com.ny.ex68_network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView result;
    Handler handler = new Handler();
    String responseData;
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);

        new Thread(new Runnable() {
            @Override
            public void run() {
                serverCall();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        process();

                    }
                });
            }
        }).start();
    }
    private void serverCall(){
        InputStream in = null;
        String str = null;
        HttpURLConnection conn = null;
        try {
            URL url =
                    new URL("http://openapi.seoul.go.kr:8088/sample/json/SeoulLibraryTime/1/5/");
            conn = (HttpURLConnection)url.openConnection();
            in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            while((str = br.readLine())!= null){
                sb.append(str);
            }
            responseData = sb.toString();
            br.close();
        }catch (Exception e){
            Log.d("my",e+"");
        }
    }
    private void process(){
        try {
            BufferedReader br = new BufferedReader(new StringReader(responseData));
            String res = br.readLine();
            msg = "서울 도서관 정보 \n\n";
            JSONObject jObject1 = new JSONObject(res);
            JSONObject jObject2 = new JSONObject(jObject1.getString("SeoulLibraryTime"));
            JSONArray jsonArray = new JSONArray(jObject2.getString("row"));
            for (int i=0; i <jsonArray.length(); i++){
                msg += "도서관이름 : " + jsonArray.getJSONObject(i).getString("LBRRY_NAME") + "\n" +
                        "위치 : " + jsonArray.getJSONObject(i).getString("CODE_VALUE") + "\n" +
                        "주소 : " + jsonArray.getJSONObject(i).getString("ADRES") + "\n" +
                        "휴무 : " + jsonArray.getJSONObject(i).getString("FDRM_CLOSE_DATE") + "\n" +
                        "전화 : " + jsonArray.getJSONObject(i).getString("TEL_NO") + "\n" +
                        ":::::::::::::::::::::::::::::::::::::::::::::::::::: \n";
            }
            result.setText(msg);
        }catch (Exception e){
            Log.d("my",e+"");
        }
    }
}
