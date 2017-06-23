package io.keepcoding.madridshops.domain.managers.cache;

import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import io.keepcoding.madridshops.domain.managers.db.ShopDAO;
import io.keepcoding.madridshops.domain.managers.network.GetAllShopsManagerCompletion;
import io.keepcoding.madridshops.domain.model.Shop;
import io.keepcoding.madridshops.domain.model.Shops;

public class GetAllShopsFromCacheManagerDAOImpl implements GetAllShopsFromCacheManager {

    WeakReference<Context> contextWeakReference;

    public GetAllShopsFromCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(@NonNull GetAllShopsFromCacheManagerCompletion completion) {
        ShopDAO dao = new ShopDAO(contextWeakReference.get());
        List<Shop> shopList = dao.query();
        if(shopList == null) {
            return;
        }
        Shops shops = Shops.from(shopList);
        completion.completion(shops);
    }
}
