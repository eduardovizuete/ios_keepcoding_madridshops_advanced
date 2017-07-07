package io.keepcoding.madridshops.domain.managers.network;

import android.support.annotation.NonNull;

import java.util.List;

import io.keepcoding.madridshops.domain.managers.network.entities.ActivityEntity;

public interface GetAllActivitiesManagerCompletion {
    void completion(@NonNull final List<ActivityEntity> activityEntityList);
}
