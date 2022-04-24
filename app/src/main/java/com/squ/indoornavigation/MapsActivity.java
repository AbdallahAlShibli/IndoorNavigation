package com.squ.indoornavigation;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationRequest;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.squ.indoornavigation.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng enginneringCollege;
    private ActivityMapsBinding binding;

    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fetchLocation();


    }

    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapsActivity.this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//        mapOptions.zOrderOnTop(true);
        mMap = googleMap;
        enginneringCollege = new LatLng(23.591966, 58.170859);

        Bitmap bitmap12 = BitmapFactory.decodeResource(getResources(), R.drawable.shadow);
        BitmapDescriptor bitmapDescriptor12 = BitmapDescriptorFactory.fromBitmap(bitmap12);

        mMap.addMarker(new MarkerOptions().position(enginneringCollege).title(getString(R.string.college_of_engineering)).icon(bitmapDescriptor12));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(enginneringCollege, 19));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(19), 100, null);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setMapType(mMap.MAP_TYPE_SATELLITE);
////        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
////            @Override
////            public void onCameraIdle() {
////                enginneringCollege = new LatLng(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude);
////                VisibleRegion visibleRegion = mMap.getProjection().getVisibleRegion();
////                LatLng topLeft = visibleRegion.farLeft;
////                LatLng bottomLeft = visibleRegion.nearLeft;
////                LatLng topRight = visibleRegion.farRight;
////                LatLng bottomRight = visibleRegion.nearRight;
////                LatLngBounds latLngBounds = new LatLngBounds.Builder().include(enginneringCollege).include(topLeft).include(topRight).include(bottomLeft).include(bottomRight).build();
////                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_background);
////                float degrees = 30;
////                Matrix matrix = new Matrix();
////                matrix.setRotate(degrees);
////                Bitmap bitmap0 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
////                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
////                GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions().image(bitmapDescriptor).position(enginneringCollege,bitmap.getWidth(), bitmap.getHeight()).transparency(0.7f);
////                mMap.addGroundOverlay(groundOverlayOptions);
////
////            }
////        });
//
//
        enginneringCollege = new LatLng(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude);
        VisibleRegion visibleRegion = mMap.getProjection().getVisibleRegion();
        LatLng topLeft = visibleRegion.farLeft;
        LatLng bottomLeft = visibleRegion.nearLeft;
        LatLng topRight = visibleRegion.farRight;
        LatLng bottomRight = visibleRegion.nearRight;
        LatLngBounds latLngBounds = new LatLngBounds.Builder().include(enginneringCollege).include(topLeft).include(topRight).include(bottomLeft).include(bottomRight).build();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_background);
        float degrees = -3;
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees);
        Bitmap bitmap0 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap0);
        GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions().image(bitmapDescriptor).position(enginneringCollege, 100f, 50f).transparency(0.1f);
        mMap.addGroundOverlay(groundOverlayOptions);
//
//
        searchMethod();//search method
//
////        getMyCurrentLocation(googleMap);//get my current location
//
        qrMethod();//draw office path
    }



    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }

    private void qrMethod() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if ((extras.getInt("QRData") == 64)) {
                PositionsModel positionsModel = new PositionsModel(mMap);
                positionsModel.get_64_path();
            }else {
//                getSearchMessage();
            }

            if ((extras.getInt("QRData") == 75)) {
                PositionsModel positionsModel = new PositionsModel(mMap);
                positionsModel.get_75_path();
            }else {
//                getSearchMessage();
            }
        }
    }



    private void searchMethod() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if ((extras.getInt("searchData") == 1) || (extras.getInt("searchData") == 2001)) {
                PositionsModel positionsModel = new PositionsModel(mMap);
                positionsModel.get_2001();
            }else {
//                getSearchMessage();
            }

            if ((extras.getInt("searchData") == 65)) {
                PositionsModel positionsModel = new PositionsModel(mMap);
                positionsModel.get_65();
            }else {
//                getSearchMessage();
            }

            if ((extras.getInt("searchData") == 2002)) {
                PositionsModel positionsModel = new PositionsModel(mMap);
                positionsModel.get_2002();
            }else {
//                getSearchMessage();
            }

        }
    }


    //search message
    private void getSearchMessage() {
        Toast.makeText(MapsActivity.this, R.string.not_found, Toast.LENGTH_SHORT).show();
    }


//    //get my location
//    private void getMyCurrentLocation(GoogleMap googleMap) {
//
//        switchBox.setOnClickListener(view -> {
//
//            boolean switchBoxState = switchBox.isChecked();
//
//            if (switchBoxState) {
//
//
//                double latitude = currentLocation.getLatitude();
//                double longitude = currentLocation.getLongitude();
//
//
//                LatLng latLng = new LatLng(latitude, longitude);
//                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(getString(R.string.current_location));
//                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 19));
//                googleMap.addMarker(markerOptions);
//            }
//
//        });
//
//    }


}