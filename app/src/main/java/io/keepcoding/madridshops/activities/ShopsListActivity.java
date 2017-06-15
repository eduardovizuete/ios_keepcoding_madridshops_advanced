package io.keepcoding.madridshops.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.model.Shop;

public class ShopsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);

        Shop.of(1, "Mi Tienda").
                setAddress("C").
                setLatitude(10).
                setLongitude(10);
    }
}
