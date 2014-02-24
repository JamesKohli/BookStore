package com.wellesleybooks;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.maps.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: James Kohli
 * Date: 6/5/12
 * Time: 5:18 PM
 */
public class GoogleMap extends MapActivity {

    List<Address> wellesleyLocation;

    @Override
    protected boolean isRouteDisplayed() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemaps);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        MapController mapController = mapView.getController();
        GeoPoint point = new GeoPoint(42295938,-71297368);
        mapController.setCenter(point);
        mapController.setZoom(15);

        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.maparrow);
        MapItemizedOverlay itemizedoverlay = new MapItemizedOverlay(drawable, this);


        OverlayItem overlayitem = new OverlayItem(point, "Wellesley Books!", "82 Central St.\nWellesley, MA 02482");

        itemizedoverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlay);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, StoreInfo.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
