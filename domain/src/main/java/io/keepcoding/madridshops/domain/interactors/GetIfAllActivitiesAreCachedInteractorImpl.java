package io.keepcoding.madridshops.domain.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class GetIfAllActivitiesAreCachedInteractorImpl implements GetIfAllActivitiesAreCachedInteractor {

    private WeakReference<Context> context;

    public GetIfAllActivitiesAreCachedInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void execute(Runnable onAllActivitiesAreCached, Runnable onAllActivitiesAreNotCached) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        boolean activitiesSaved = preferences.getBoolean(SetAllActivitiesAreCachedInteractor.ACTIVITIES_SAVED, false);

        if (activitiesSaved) {
            onAllActivitiesAreCached.run();
        } else {
            onAllActivitiesAreNotCached.run();
        }
    }
}
