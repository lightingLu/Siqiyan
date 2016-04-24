package com.example.three.siqiyan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.detail_webview)
    public WebView webview;
    @Bind(R.id.web_back)
    public ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        back.setOnClickListener(this);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);//可以通过注入js来获取网页内容，然后进行过滤
        settings.setBuiltInZoomControls(true);


        webview.setWebViewClient(new WebViewClient() {
            //当页面打开的时候
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i("=======", "页面开始加载了");
            }

            //所有加载的请求都会在这里回调
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            //页面加载结束的时候
            @Override
            public void onPageFinished(WebView view, String url) {
                //只加载主页面的信息
                view.loadUrl("javascript:(function() { " +
                        "document.body.innerHTML = " +
                        "document.body.innerHTML.replace(/Copyright/g,'Google');" +
                        "})()");
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            //设置加载的进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        webview.loadUrl(url);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.web_back:
                Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(intent);
//                onKeyDown(KeyEvent.KEYCODE_BACK,);
                break;
            default:
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            return super.onKeyDown(keyCode, event);

    }


}
