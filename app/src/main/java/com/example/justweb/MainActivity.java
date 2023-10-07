package com.example.justweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Intent intent=getIntent();
        if(intent!=null) {
            String action=intent.getAction();
            String type=intent.getType();
            if(Intent.ACTION_SEND.equals(action) && type!=null) {
                webView.loadUrl(handleTextData(intent));
            } else if (Intent.ACTION_VIEW.equals(action)) {
                // Handle the URL passed via ACTION_VIEW
                String url = intent.getDataString();
                if (url != null) {
                    webView.loadUrl(url);
                } else {
                    webView.loadUrl("https://joshuaaquino.xyz/blog/11");
                }
            } else {
                webView.loadUrl("https://joshuaaquino.xyz/blog/12");
            }
        } else {
            webView.loadUrl("https://joshuaaquino.xyz/blog/13");
        }
    }

    private String handleTextData(Intent intent) {
        String textdata=intent.getStringExtra(Intent.EXTRA_TEXT);
        if(textdata!=null) {
            return textdata;
        }
        return "https://joshuaaquino.xyz";
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}