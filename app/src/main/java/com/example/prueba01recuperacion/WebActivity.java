package com.example.prueba01recuperacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebActivity extends AppCompatActivity {
    WebView webView;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.wv1);
        String url = getIntent().getStringExtra("url");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://"+url);


        buttonBack = (Button) findViewById(R.id.btn_atras);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });



    }
    public void back(){
        finish();
    }
}

