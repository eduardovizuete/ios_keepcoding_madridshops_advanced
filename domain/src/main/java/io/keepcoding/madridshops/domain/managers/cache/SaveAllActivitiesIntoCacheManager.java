package io.keepcoding.madridshops.domain.managers.cache;

import io.keepcoding.madridshops.domain.model.Activity.Activities;

public interface SaveAllActivitiesIntoCacheManager {
    void execute(Activities activities, Runnable completion);
}
