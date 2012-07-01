package com.bookstore;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: James Kohli
 * Date: 6/5/12
 * Time: 6:06 PM
 */
public class MapItemizedOverlay extends ItemizedOverlay{

    private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
    Context mContext;

    public MapItemizedOverlay(Drawable defaultMarker) {
        super(boundCenterBottom(defaultMarker));
    }
    public MapItemizedOverlay(Drawable defaultMarker, Context context) {
        super(boundCenterBottom(defaultMarker));
        mContext = context;
    }

    @Override
    protected OverlayItem createItem(int i) {
        return mOverlays.get(i);
    }

    @Override
    public int size() {
        return mOverlays.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addOverlay(OverlayItem overlay) {
        mOverlays.add(overlay);
        populate();
    }

    @Override
    protected boolean onTap(int index) {
        OverlayItem item = mOverlays.get(index);
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle(item.getTitle());
        dialog.setMessage(item.getSnippet());
        dialog.show();
        return true;
    }
}
