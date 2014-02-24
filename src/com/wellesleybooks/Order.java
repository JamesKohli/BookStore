package com.wellesleybooks;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;

/**
 * Created with IntelliJ IDEA.
 * User: James Kohli
 * Date: 7/1/12
 * Time: 3:37 PM
 */
public class Order extends Activity {

    WebView webView;
    LoadBrowser loadBrowser;
    Thread browserThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.order);

        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refreshactionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.refresh:
                webView.loadUrl( "javascript:window.location.reload( true )" );
                return true;
            case R.id.back:
                webView.goBack();
                return true;
            case R.id.cancel:
                browserThread.run();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initialize() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        webView = (WebView) findViewById(R.id.wvOrder);
        webView.getSettings().setBuiltInZoomControls(true);
        loadBrowser = new LoadBrowser();
        browserThread = new Thread(loadBrowser);
        browserThread.run();
    }

    private class LoadBrowser implements Runnable{

        @Override
        public void run() {
            webView.setWebViewClient(new BookstoreWebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            try{
                webView.loadUrl("http://www.wellesley.indiebound.com/how-place-online-order");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
