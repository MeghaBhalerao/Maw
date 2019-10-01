package com.example.maw.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.example.maw.R;


public class WebviewActivity extends AppCompatActivity {


    private WebView webView;
    private String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = (WebView) findViewById(R.id.webView1);
        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");
        startWebView(url);
    }

    private void startWebView(String url) {

        webView.setWebViewClient(new WebViewClient()
        {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(WebviewActivity.this);
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();
                    //progressWheel.setVisibility(View.VISIBLE);
                }
            }

            public void onPageFinished(WebView view, String url) {
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        // progressWheel.setVisibility(View.GONE);
                      //  progressDialog = null;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });

        // Javascript inabled on webview
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);


    }
    @Override
    // Detect when the back button is pressed
    public void onBackPressed()
    {
        goBack();
    }

    private void goBack()
    {
        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }


}
