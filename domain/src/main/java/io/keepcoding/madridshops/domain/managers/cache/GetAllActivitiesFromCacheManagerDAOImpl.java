package io.keepcoding.madridshops.domain.managers.cache;

import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import io.keepcoding.madridshops.domain.managers.db.ActivityDAO;
import io.keepcoding.madridshops.domain.model.Activity.Activities;
import io.keepcoding.madridshops.domain.model.Activity.Activity;

public class GetAllActivitiesFromCacheManagerDAOImpl implements GetAllActivitiesFromCacheManager {

    WeakReference<Context> contextWeakReference;

    public GetAllActivitiesFromCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(@NonNull GetAllActivitiesFromCacheManagerCompletion completion) {
        ActivityDAO dao = new ActivityDAO(contextWeakReference.get());
        List<Activity> activityList = dao.query();
        if(activityList == null) {
            return;
        }
        Activities activities = Activities.from(activityList);
        completion.completion(activities);
    }
}
