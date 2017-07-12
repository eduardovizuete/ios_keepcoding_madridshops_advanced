package io.keepcoding.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import io.keepcoding.madridshops.activities.ActivityDetailActivity;
import io.keepcoding.madridshops.activities.ActivityListActivity;
import io.keepcoding.madridshops.activities.MainActivity;
import io.keepcoding.madridshops.activities.ShopDetailActivity;
import io.keepcoding.madridshops.activities.ShopListActivity;
import io.keepcoding.madridshops.domain.model.Activity.Activity;
import io.keepcoding.madridshops.domain.model.Shop.Shop;

import static io.keepcoding.madridshops.util.Constants.INTENT_ACTIVITY_DETAIL;
import static io.keepcoding.madridshops.util.Constants.INTENT_SHOP_DETAIL;

public class Navigator {
    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity) {
        assert(mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopListActivity.class);
        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromShopListActivityToShopDetailActivity(@NonNull final ShopListActivity shopListActivity, final Shop shop, final int position) {
        final Intent i = new Intent(shopListActivity, ShopDetailActivity.class);
        i.putExtra(INTENT_SHOP_DETAIL, shop);

        shopListActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromMainActivityToActivityListActivity(@NonNull final MainActivity mainActivity) {
        assert(mainActivity != null);

        final Intent i = new Intent(mainActivity, ActivityListActivity.class);
        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromActivityListActivityToActivityDetailActivity(@NonNull final ActivityListActivity activityListActivity, final Activity activity, final int position) {
        final Intent i = new Intent(activityListActivity, ActivityDetailActivity.class);
        i.putExtra(INTENT_ACTIVITY_DETAIL, activity);

        activityListActivity.startActivity(i);

        return i;
    }

}
