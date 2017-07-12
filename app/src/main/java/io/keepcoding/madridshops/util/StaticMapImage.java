package io.keepcoding.madridshops.util;

import android.support.annotation.NonNull;

import io.keepcoding.madridshops.domain.model.Activity.Activity;
import io.keepcoding.madridshops.domain.model.Shop.Shop;

public class StaticMapImage {
    public static String getMapImageUrl(@NonNull final Shop shop) {
        String url = String.format("http://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=17&size=320x220",
                shop.getLatitude(), shop.getLongitude());

        return url;
    }

    public static String getActivityMapImageUrl(@NonNull final Activity activity) {
        String url = String.format("http://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=17&size=320x220",
                activity.getLatitude(), activity.getLongitude());

        return url;
    }
}
