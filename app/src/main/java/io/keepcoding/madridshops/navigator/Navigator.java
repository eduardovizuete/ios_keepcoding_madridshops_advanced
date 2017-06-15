package io.keepcoding.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import io.keepcoding.madridshops.activities.MainActivity;
import io.keepcoding.madridshops.activities.ShopsListActivity;

public class Navigator {

    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity){
        assert (mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopsListActivity.class);
        mainActivity.startActivity(i);

        return i;
    }
}
