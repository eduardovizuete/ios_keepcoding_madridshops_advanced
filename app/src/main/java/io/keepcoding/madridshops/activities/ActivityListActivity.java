package io.keepcoding.madridshops.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.model.Activity.Activity;

public class ActivityListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Activity.of(1, "Mi Activity").
                setAddress("Address").
                setLatitude(10).
                setLongitude(10);
    }
}
