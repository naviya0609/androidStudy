package com.ny.ex30_webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01);

        WebView webView =findViewById(R.id.webView1);
        String custHtml = "<body style=\"background-color:powderblue;\">\n" +
                "\n" +
                "<h1>This is a heading</h1>\n" +
                "<p>This is a paragraph.</p>\n" +
                "\n" +
                "</body>";
        webView.loadData(custHtml,"text/html","utf-8");

    }
}
