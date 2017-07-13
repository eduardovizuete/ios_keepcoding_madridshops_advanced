package io.keepcoding.madridshops.domain.managers.cache;

import android.support.annotation.NonNull;

import io.keepcoding.madridshops.domain.model.Activity.Activities;

public interface GetAllActivitiesFromCacheManagerCompletion {
    void completion(@NonNull final Activities activities);
}
