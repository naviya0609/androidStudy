package com.ny.ex66_network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ny.ex66_network.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    TextView result;
    Handler handler = new Handler();
    String conn_msg, sax_mag1 ,sax_mag2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("my","1");
                // 스레드 처리해서 서버에 접속
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 서버에 접속 해서 데이터 가져오기(conn_msg에 저장되어있다.)
                        serverConnect("http://172.16.14.1:8090/server01/MyController01");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                // 정보를 받아서 파싱하고  데이터를 result에 넣기
                                process01();
                            }
                        });
                    }
                }).start();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverConnect("http://172.16.14.1:8090/server01/MyController02");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                process02();
                            }
                        });
                    }
                }).start();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("my","1");
                // 스레드 처리해서 서버에 접속
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 서버에 접속 해서 데이터 가져오기(conn_msg에 저장되어있다.)
                        serverConnect("http://172.16.14.1:8090/server01/MyController02");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                // 정보를 받아서 파싱하고  데이터를 result에 넣기
                                process03();
                            }
                        });
                    }
                }).start();
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("my","1");
                // 스레드 처리해서 서버에 접속
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 서버에 접속 해서 데이터 가져오기(conn_msg에 저장되어있다.)
                        serverConnect("http://172.16.14.1:8090/server01/MyController02");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                // 정보를 받아서 파싱하고  데이터를 result에 넣기
                                process04();
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void serverConnect(String str){
        InputStream in = null;
        String msg = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(str);
            conn = (HttpURLConnection)url.openConnection();
            in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            while ((msg=br.readLine()) != null){
                sb.append(msg);
            }
            conn_msg = sb.toString();
            br.close();
        }catch (Exception e){
            conn.disconnect();
        }
    }

    // DOM 방식의 XML 파싱
    private void process01(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(conn_msg)));

            // 태그 구하기
            NodeList person = document.getElementsByTagName("person");
            NodeList idx = document.getElementsByTagName("idx");
            NodeList id = document.getElementsByTagName("id");
            NodeList pw = document.getElementsByTagName("pw");
            NodeList name = document.getElementsByTagName("name");
            NodeList age = document.getElementsByTagName("age");
            NodeList addr = document.getElementsByTagName("addr");
            NodeList regdate = document.getElementsByTagName("regdate");

            StringBuffer sb = new StringBuffer("XML_파싱(DOM) 방식");
            for (int i=0; i<person.getLength(); i++){
                sb.append("\n");
                sb.append("\n");
                // getFirstChild().getNodeValue() => 태스사이에 존재하는 텍스트 가져오기
                sb.append(
                        idx.item(i).getFirstChild().getNodeValue()+",  " +
                                id.item(i).getFirstChild().getNodeValue()+",  " +
                                pw.item(i).getFirstChild().getNodeValue()+",  " +
                                name.item(i).getFirstChild().getNodeValue()+",  " +
                                age.item(i).getFirstChild().getNodeValue()+",  " +
                                addr.item(i).getFirstChild().getNodeValue()+",  " +
                                regdate.item(i).getFirstChild().getNodeValue()
                );
            }

            result.setText(sb.toString());

        }catch (Exception e){
        }
    }

    //DOM 방식 : 속성 가져오기
    private void process02(){
        try{
            StringBuffer sb= new StringBuffer("XML_Attr파싱 (DOM방식)");
            //앞의 세줄 동일
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(conn_msg)));

            //person 태그
            NodeList person = document.getElementsByTagName("person");

            for(int i = 0 ; i< person.getLength(); i++){
                sb.append("\n");
                sb.append("\n");
                sb.append(
                        //태그안의 속성
                        ((Element)person.item(i)).getAttribute("idx")       +","+
                                ((Element)person.item(i)).getAttribute("id")        +","+
                                ((Element)person.item(i)).getAttribute("pw")        +","+
                                ((Element)person.item(i)).getAttribute("name")      +","+
                                ((Element)person.item(i)).getAttribute("age")       +","+
                                ((Element)person.item(i)).getAttribute("addr")      +","+
                                ((Element)person.item(i)).getAttribute("regdate")   +","
                );
            }
            result.setText(sb.toString());
        }catch (Exception e){

        }
    }

    //DOM 방식 : 속성 가져오기
    private void process03(){
        try{
         sax_mag1 = "XML 파싱 (sax) \n\n";
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLReader sax = saxParser.getXMLReader();
            sax.setContentHandler(new MyXMLProcess01());
            sax.parse(new InputSource(new StringReader(conn_msg)));
        }catch (Exception e){

        }
    }
    //DOM 방식 : 속성 가져오기
    private void process04(){
        try{
            sax_mag1 = "XML ATTR 파싱 (sax) \n\n";
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLReader sax = saxParser.getXMLReader();
            sax.setContentHandler(new MyXMLProcess02());
            sax.parse(new InputSource(new StringReader(conn_msg)));
        }catch (Exception e){

        }
    }
    class MyXMLProcess01 extends DefaultHandler {
        //시작태그 끝태그 사이의 text 추출 메소드
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String str = new String(ch, start,length);
            if(str.trim().length() != 0 && !str.contains("-")){
                sax_mag1 +=str+",";
            }else{
                sax_mag1 +=str+"\n\n"; //(내부적으로 for가 돎)
            }
            result.setText(sax_mag1);
        }
    }

    class MyXMLProcess02 extends DefaultHandler {
        //시작태그 안의 속성값 추출 메소드
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            //String qName== 태그명, Attributes attributes == 속성값
            if(qName.equals("person")){ //person 태그의 속성값
                sax_mag2 += attributes.getValue("idx")         + ", " +
                             attributes.getValue("id")          + ", " +
                             attributes.getValue("pw")          + ", " +
                             attributes.getValue("name")        + ", " +
                             attributes.getValue("addr")        + ", " +
                             attributes.getValue("regdate")    + "\n\n";
            }
            result.setText(sax_mag2);
        }
    }
}
