package io.keepcoding.madridshops.domain.managers.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface NetworkManagerActivities {
    void getActivitiesFromServer(@NonNull final GetAllActivitiesManagerCompletion completion,
                                 @Nullable final ManagerErrorCompletion errorCompletion);
}
