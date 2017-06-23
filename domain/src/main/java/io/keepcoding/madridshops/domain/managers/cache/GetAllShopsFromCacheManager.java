package io.keepcoding.madridshops.domain.managers.cache;

import android.support.annotation.NonNull;

import io.keepcoding.madridshops.domain.managers.network.GetAllShopsManagerCompletion;

public interface GetAllShopsFromCacheManager {
    void execute(@NonNull final GetAllShopsFromCacheManagerCompletion completion);
}
