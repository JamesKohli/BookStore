package com.bookstore;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

/**
 * Created with IntelliJ IDEA.
 * User: James Kohli
 * Date: 6/4/12
 * Time: 4:00 PM
 */
public class Contact extends Activity implements View.OnClickListener {

    private EditText subject, body;
    private Button sendEmail, phone;
    String subjectString, bodyString;
    TabHost tabHost;
    TabHost.TabSpec tabSpec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.contact);

        initialize();

    }

    private void callStore() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:17813757363"));
            startActivity(callIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("Call Store", "Call failed", e);
        }
    }

    private void initialize() {
        initializeTabs();
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        subject = (EditText) findViewById(R.id.etContactEmailSubject);
        body = (EditText) findViewById(R.id.etContactEmailBody);
        sendEmail = (Button) findViewById(R.id.bContactSendEmail);
        sendEmail.setOnClickListener(this);
        phone = (Button) findViewById(R.id.bContactPhone);
        phone.setOnClickListener(this);
    }

    private void initializeTabs() {
        tabHost = (TabHost) findViewById(R.id.thContact);
        tabHost.setup();
        tabSpec = tabHost.newTabSpec("tab1");
        tabSpec.setContent(R.id.rlContact1);
        tabSpec.setIndicator("Email us");
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("tab2");
        tabSpec.setContent(R.id.llContact1);
        tabSpec.setIndicator("Call us");
        tabHost.addTab(tabSpec);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, MainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);
        return true;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bContactSendEmail:
                initializeEmailString();
                break;
            case R.id.bContactPhone:
                callStore();
                break;

        }

    }

    private void initializeEmailString() {
        subjectString = "[Android] " + subject.getText().toString();
        bodyString = body.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, R.string.wellesleyemail);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subjectString);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_TEXT, bodyString);
        startActivity(emailIntent);
    }
}
