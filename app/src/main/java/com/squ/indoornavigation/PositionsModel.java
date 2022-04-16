package com.squ.indoornavigation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PositionsModel {
    private GoogleMap mMap;

    public PositionsModel(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public void get_2001() {

        LatLng latLng = new LatLng(23.591978, 58.170475);
        mMap.addMarker(new MarkerOptions().position(latLng).title("2001 DEAN’S MEETING ROOM"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 19));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(19), 100, null);
        mMap.getUiSettings().setMapToolbarEnabled(false);
//        mMap.setMapType(mMap.MAP_TYPE_SATELLITE);
    }


    public void get_65() {

        LatLng latLng = new LatLng(23.592106, 58.170836);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Elevator");
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 19));
        mMap.addMarker(markerOptions);
    }
}
