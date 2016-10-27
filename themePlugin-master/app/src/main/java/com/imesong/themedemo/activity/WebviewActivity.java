package com.imesong.themedemo.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.imesong.themedemo.R;

/**
 * Created by ping on 2016/10/26.
 */

public class WebviewActivity extends Activity {
    String game = "http://www.1905game.com/game/info/1063.html";
    String url = "http://www.1905game.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView webview = (WebView) findViewById(R.id.webview);
        //访问页面中有js必须设置此属性 !!!!!!!
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        //支持缩放
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
//        webview.getSettings().setPluginsEnabled(true);//没有此方法

        //将图片调整到审核webview的大小
        webview.getSettings().setUseWideViewPort(true);

//        "Uncaught TypeError: Cannot read property 'setItem' of null",  在登陆游戏的时候 登陆 到半截一直不动  查看log发现这样的日志//h5storage  主要 为h5存储数据
        //解决办法：http://blog.sina.com.cn/s/blog_96a146890102viry.html
        webview.getSettings().setDomStorageEnabled(true);

                //设置渲染的优先级
        webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webview.setVerticalScrollBarEnabled(false);
        webview.loadUrl(url);//
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }


        });
    }
}
