package com.ny.ex67_network;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    WebView webView ;
    Handler handler = new Handler();
    String year, month, day, hour;
    ArrayList<VO> list = new ArrayList<>();
    final String weatherURL = "http://www.kma.go.kr/XML/weather/sfc_web_map.xml";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        receiverForCast();
    }
    private void receiverForCast(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 정보가 list에 담겨있음
                bulidXmlPullParser(getStreamFormUrl());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // webView에 넣기
                        showData();
                    }
                });
            }
        }).start();
    }

    private void showData(){
        StringBuffer sb = new StringBuffer();
        sb.append("<html><body>");
        sb.append("<h2 align='center'>** 기상청 제공 날씨 정보 **</h2>");
        sb.append(year+"년"+month+"월"+day+"일"+hour+"시 현재 <br>");
        sb.append("<table width='100%' border='1'>");
        sb.append("<thead>");
        sb.append("<tr><th>지역</th><th>날씨상태</th><th>온도</th><th>이미지</th></tr></thead>");
        sb.append("<tbody align='center'>");
        for(VO k :list) {
            sb.append("<tr>");
            sb.append("<td>"+k.getLocal()+"</td>");
            sb.append("<td>"+k.getDesc()+"</td>");
            sb.append("<td>"+k.getTa()+"</td>");
            if(k.getIcon().equals("")){
                sb.append("<td></td>");
            }else{
                sb.append("<td><img src='http://www.kma.go.kr/images/icon/NW/NB"
                        +k.getIcon()+".png'></td>");
            }
            sb.append("</tr>");
        }
        sb.append("</tbody></table></body></html>");


        webView.loadDataWithBaseURL(null, sb.toString(),
                "text/html","utf-8",null);
    }
    //해당사이트에 접속
    private void bulidXmlPullParser(InputStream stream){
        String local = "", desc="", ta="", icon ="";
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(stream,"utf-8");
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT){
                if(parser.getEventType() == XmlPullParser.START_TAG){
                    if(parser.getName().equals("weather")){
                        year = parser.getAttributeValue(0);
                        month = parser.getAttributeValue(1);
                        day = parser.getAttributeValue(2);
                        hour = parser.getAttributeValue(3);
                    }
                    if(parser.getName().equals("local")){
                        icon = parser.getAttributeValue(1);
                        desc = parser.getAttributeValue(2);
                        ta = parser.getAttributeValue(3);
                    }
                }else if(parser.getEventType() == XmlPullParser.TEXT){
                    local = parser.getText();
                }else if(parser.getEventType() == XmlPullParser.END_TAG){
                    if(! local.trim().equals("")){
                        list.add(new VO(local,desc,ta,icon));
                    }
                }
                parser.next();
            }
        }catch (Exception e){
        }
    }
    // 해당 사이트에 접속해서 inputStream 해결
    private InputStream getStreamFormUrl(){
        InputStream in  = null;
        String msg = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(weatherURL);
            conn = (HttpURLConnection)url.openConnection();
            in = conn.getInputStream();
        }catch (Exception e){
        }
        return in;
    }
}
