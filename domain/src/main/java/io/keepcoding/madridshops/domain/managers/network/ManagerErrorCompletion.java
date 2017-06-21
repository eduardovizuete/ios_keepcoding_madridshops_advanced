package io.keepcoding.madridshops.domain.managers.network;

import android.support.annotation.NonNull;

public interface ManagerErrorCompletion {
    public void onError(@NonNull final String errorDescription);
}
