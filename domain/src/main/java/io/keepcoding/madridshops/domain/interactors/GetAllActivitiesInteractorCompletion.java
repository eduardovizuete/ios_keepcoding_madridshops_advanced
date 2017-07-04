package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;

import io.keepcoding.madridshops.domain.model.Activity.Activities;

public interface GetAllActivitiesInteractorCompletion {
    void completion(@NonNull final Activities activities);
}
