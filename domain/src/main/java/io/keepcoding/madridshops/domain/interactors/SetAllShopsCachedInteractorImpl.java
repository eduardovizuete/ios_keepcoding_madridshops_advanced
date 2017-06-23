package io.keepcoding.madridshops.domain.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class SetAllShopsCachedInteractorImpl implements SetAllShopsCachedInteractor {

    private WeakReference<Context> context;

    public SetAllShopsCachedInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(boolean shopsSaved) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(SetAllShopsCachedInteractorImpl.SHOPS_SAVED, shopsSaved);

        editor.commit();
    }
}
