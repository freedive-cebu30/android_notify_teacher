package com.herokuapp.aqueous_spire_22637.notifyteacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by joji on 2017/04/19.
 */

public class PageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);
        WebView page = (WebView)findViewById(R.id.page);
        page.setWebViewClient(new WebViewClient());
        page.loadUrl("https://aqueous-spire-22637.herokuapp.com/");
    }
}
