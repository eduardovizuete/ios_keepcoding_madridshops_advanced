package io.keepcoding.madridshops.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.model.Activity.Activity;
import io.keepcoding.madridshops.util.Constants;
import io.keepcoding.madridshops.util.StaticMapImage;

public class ActivityDetailActivity extends AppCompatActivity {

    @BindView(R.id.activity_detail__activity_name) TextView name;
    @BindView(R.id.activity_detail__activity_description) TextView description;
    @BindView(R.id.activity_detail__activity_address) TextView address;
    @BindView(R.id.activity_detail__activity_image) ImageView activityImage;
    @BindView(R.id.activity_detail__activity_map) ImageView activityMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            Activity activity = (Activity) intent.getSerializableExtra(Constants.INTENT_ACTIVITY_DETAIL);
            name.setText(activity.getName());
            address.setText(activity.getAddress());
            description.setText(activity.getDescription_es());
            Picasso.with(this).
                load(activity.getImageUrl()).
                placeholder(R.drawable.shopplaceholder).
                into(activityImage);

                String staticMapUrl = StaticMapImage.getActivityMapImageUrl(activity);
                Picasso.with(this).
                    load(staticMapUrl).
                    placeholder(R.drawable.map_placeholder).
                    into(activityMap);
        }
    }
}
