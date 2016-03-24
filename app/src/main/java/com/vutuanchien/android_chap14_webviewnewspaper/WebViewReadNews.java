package com.vutuanchien.android_chap14_webviewnewspaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewReadNews extends AppCompatActivity {

    WebView webView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_readnews);
        webView = (WebView) findViewById(R.id.readnews);
        intent = getIntent();
        String url = intent.getStringExtra("link");
        webView.loadUrl(url);
        webView.requestFocus();
    }
}
