package com.walxy.mallproject.mvp.view.activity;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.walxy.mallproject.R;
import com.walxy.mallproject.base.BaseActivity;
import com.walxy.mallproject.mvp.view.AppConstans;


public class Pictrue extends BaseActivity {


    private WebView webView;
//    private ImageView pictrue;

    public int getLayoutID() {
        return R.layout.activity_pictrue;
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void initView() {
//        pictrue = (ImageView) findViewById(R.id.pictrue);
        webView = (WebView)findViewById(R.id.webView);
        Intent intent=getIntent();
        String goods_id = intent.getStringExtra("goods_id");
        webView.loadUrl(AppConstans.LINK_MOBILE_GOODS_BODY+100002);
        // 设置WebView的客户端
        //声明webSetings子类
        WebSettings webSettings=webView.getSettings();
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });


    }

    @Override
    public void initData() {

    }
}
