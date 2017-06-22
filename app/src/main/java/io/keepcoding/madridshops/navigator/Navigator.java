package io.keepcoding.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import io.keepcoding.madridshops.activities.MainActivity;
import io.keepcoding.madridshops.activities.ShopDetailActivity;
import io.keepcoding.madridshops.activities.ShopsListActivity;
import io.keepcoding.madridshops.domain.model.Shop;

import static io.keepcoding.madridshops.util.Constants.INTENT_SHOP_DETAIL;

public class Navigator {

    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity){
        assert (mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopsListActivity.class);
        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromShopListActivityToShopDetailActivity(@NonNull final ShopsListActivity shopsListActivity, final Shop shop, final int position){
        assert (shopsListActivity != null);

        final Intent i = new Intent(shopsListActivity, ShopDetailActivity.class);
        i.putExtra(INTENT_SHOP_DETAIL, shop);

        shopsListActivity.startActivity(i);

        return i;
    }
}
