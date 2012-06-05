package com.bookstore;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created with IntelliJ IDEA.
 * User: James Kohli
 * Date: 6/4/12
 * Time: 4:00 PM
 */
public class Contact extends Activity implements View.OnClickListener {

    private EditText subject, body;
    private Button sendEmail;
    String subjectString, bodyString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.contact);

        initialize();

    }

    private void initialize() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        subject = (EditText) findViewById(R.id.etContactEmailSubject);
        body = (EditText) findViewById(R.id.etContactEmailBody);
        sendEmail = (Button) findViewById(R.id.bContactSendEmail);
        sendEmail.setOnClickListener(this);
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
