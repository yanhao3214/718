package com.yh.jiran.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yh.jiran.R;
import com.yh.jiran.utils.Paths;

/**
 * @author: 闫昊
 * @date: 2018/8/6
 * @function: APP内置浏览器，WebView
 */
@Route(path = Paths.PATH_WEBVIEW_ACTIVITY)
public class WebViewActivity extends ImmerseActivity {

    private WebView mWebView;
    private ProgressBar mProgressBar;


    @Override
    protected int setContent() {
        return R.layout.activity_webview_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        mWebView = findViewById(R.id.web_view);
        mProgressBar = findViewById(R.id.progress_bar);
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("title");
//        getSupportActionBar().setTitle(title);
        final String url = bundle.getString("url");
        configWebView(mWebView, url);
        mWebView.loadUrl(url);
    }

    /**
     * 配置WebView参数
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void configWebView(WebView webView, String url) {
        WebSettings settings = webView.getSettings();
        //允许使用JS
        settings.setJavaScriptEnabled(true);
        //支持屏幕缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //显示缩放按钮
        settings.setDisplayZoomControls(true);
        //不使用缓存，仅从网络加载
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new WeChatWebViewClient(url));
        webView.setWebChromeClient(new WeChatWebChromeClient());
    }

    private class WeChatWebViewClient extends WebViewClient {
        private String url;

        WeChatWebViewClient(String url) {
            this.url = url;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mProgressBar.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.GONE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressBar.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
        }

        /**
         * 截获动作，本地显示，而不跳转到外部浏览器
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return true;
        }
    }

    private class WeChatWebChromeClient extends WebChromeClient {

        /**
         * 实时更新进度条
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
        }
    }

    /**
     * 支持网页返回
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mWebView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 释放资源
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
    }
}
