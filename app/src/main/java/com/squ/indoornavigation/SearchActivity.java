package com.squ.indoornavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    private Button searchB;
    private EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchB = findViewById(R.id.button_search);
        searchBox = findViewById(R.id.search_box);

        searchB.setOnClickListener(view -> {

            if(searchBox.getText().toString().trim().matches("\\d+")) {
                int OfficeNo = Integer.parseInt(searchBox.getText().toString().trim());

                Intent intent = new Intent(SearchActivity.this, MapsActivity.class);
                intent.putExtra("searchData", OfficeNo);
                startActivity(intent);

            }else {
                Toast.makeText(SearchActivity.this, "Please enter office number as number!.", Toast.LENGTH_SHORT).show();
            }

        });

    }
}