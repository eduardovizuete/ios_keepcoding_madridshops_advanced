package io.keepcoding.madridshops.domain.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class GetIfAllShopsAreCachedInteractorImpl implements GetIfAllShopsAreCachedInteractor {

    private WeakReference<Context> context;

    public GetIfAllShopsAreCachedInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }
    @Override
    public void executed(Runnable onAllShopsAreCached, Runnable onAllShopsAreNotCached) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        boolean shopsSaved = preferences.getBoolean(SetAllShopsCachedInteractor.SHOPS_SAVED, false);

        if (shopsSaved) {
            onAllShopsAreCached.run();
        } else {
            onAllShopsAreNotCached.run();
        }

    }
}
