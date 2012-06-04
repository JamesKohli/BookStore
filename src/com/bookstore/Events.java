package com.bookstore;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TabHost;

/**
 * Created with IntelliJ IDEA.
 * User: James Kohli
 * Date: 6/3/12
 * Time: 3:53 PM
 */
public class Events extends Activity {

    private WebView info, events, blog;
    private TabHost tabHost;
    private TabHost.TabSpec tabSpec;
    private LoadBrowsers loadBrowsers;
    Thread browserThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.events);
        initialize();
    }

    private void initialize() {
        initializeTabs();
        initializeBrowsers();
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
                browserThread.run();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initializeBrowsers() {
        info = (WebView) findViewById(R.id.wvEventsInfo);
        blog = (WebView) findViewById(R.id.wvEventsBlog);
        events = (WebView) findViewById(R.id.wvEventsEvents);


        loadBrowsers = new LoadBrowsers(info, blog, events);
        browserThread = new Thread(loadBrowsers);
        browserThread.run();


    }

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        finish();
    }

    private void initializeTabs() {
        tabHost = (TabHost) findViewById(R.id.thEvents);
        tabHost.setup();
        tabSpec = tabHost.newTabSpec("tab1");
        tabSpec.setContent(R.id.llEventsInfo);
        tabSpec.setIndicator("News");
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("tab2");
        tabSpec.setContent(R.id.llEventsBlog);
        tabSpec.setIndicator("Blog");
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("tab3");
        tabSpec.setContent(R.id.llEventsEvents);
        tabSpec.setIndicator("Events");
        tabHost.addTab(tabSpec);
    }

    private class LoadBrowsers implements Runnable {

        WebView info, blog, events;

        public LoadBrowsers(WebView info, WebView blog, WebView events) {
            this.info = info;
            this.blog = blog;
            this.events = events;
        }

        @Override
        public void run() {

            info.setWebViewClient(new BookstoreWebViewClient());
            info.getSettings().setJavaScriptEnabled(true);
            info.getSettings().setLoadWithOverviewMode(true);
            info.getSettings().setUseWideViewPort(true);
            try{
                info.loadUrl("http://archive.constantcontact.com/fs032/1104982944822/archive/1110056827386.html");
            } catch (Exception e){
                e.printStackTrace();
            }

            blog.setWebViewClient(new BookstoreWebViewClient());
            blog.getSettings().setJavaScriptEnabled(true);
            blog.getSettings().setLoadWithOverviewMode(true);
            blog.getSettings().setUseWideViewPort(true);
            try{
                blog.loadUrl("http://wellesleybooks.blogspot.com/");
            } catch (Exception e){
                e.printStackTrace();
            }

            events.setWebViewClient(new BookstoreWebViewClient());
            events.getSettings().setJavaScriptEnabled(true);
            events.getSettings().setLoadWithOverviewMode(true);
            events.getSettings().setUseWideViewPort(true);
            try{
                events.loadUrl("http://wellesley.indiebound.com/event#inside-content");
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
