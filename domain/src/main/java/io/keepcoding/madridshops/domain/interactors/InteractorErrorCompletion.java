package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;

public interface InteractorErrorCompletion {
    public void onError(@NonNull final String errorDescription);
}
