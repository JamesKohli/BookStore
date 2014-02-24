package com.wellesleybooks;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created with IntelliJ IDEA.
 * User: James Kohli
 * Date: 6/3/12
 * Time: 4:48 PM
 */
public class BookstoreWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
