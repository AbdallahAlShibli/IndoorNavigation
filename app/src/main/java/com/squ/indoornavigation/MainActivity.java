package com.squ.indoornavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.squ.indoornavigation.qrScanner.QRCodeActivity;

public class MainActivity extends AppCompatActivity {

    private Button map, scan, search, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = findViewById(R.id.map);
        search = findViewById(R.id.search);
        scan = findViewById(R.id.qr_code);
        about = findViewById(R.id.about);

        map.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
        });

        scan.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), QRCodeActivity.class));
        });

        search.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SearchActivity.class));
        });

        about.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AboutActivity.class));
        });

    }
}