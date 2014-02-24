package com.wellesleybooks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created with IntelliJ IDEA.
 * User: James Kohli
 * Date: 5/26/12
 * Time: 6:49 PM
 */

public class MainMenu extends Activity implements View.OnClickListener {

    private Button contact, news, orders, info;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialize();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar, menu);
        return true;
    }

    private void initialize() {
        contact = (Button) findViewById(R.id.bMainContact);
        news = (Button) findViewById(R.id.bMainNews);
        orders = (Button) findViewById(R.id.bMainOrders);
        info = (Button) findViewById(R.id.bMainStoreInfo);
        contact.setOnClickListener(this);
        news.setOnClickListener(this);
        orders.setOnClickListener(this);
        info.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bMainContact:
                Intent contactIntent = new Intent(MainMenu.this, Contact.class);
                startActivity(contactIntent);
                break;
            case R.id.bMainNews:
                Intent newsIntent = new Intent(MainMenu.this, Events.class);
                startActivity(newsIntent);
                break;
            case R.id.bMainOrders:
                Intent orderIntent = new Intent(MainMenu.this, Order.class);
                startActivity(orderIntent);
                break;
            case R.id.bMainStoreInfo:
                Intent infoIntent = new Intent(MainMenu.this, StoreInfo.class);
                startActivity(infoIntent);
                break;
        }
    }
}
