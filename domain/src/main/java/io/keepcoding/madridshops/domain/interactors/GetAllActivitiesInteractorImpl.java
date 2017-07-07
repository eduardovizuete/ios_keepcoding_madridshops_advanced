package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import io.keepcoding.madridshops.domain.managers.network.GetAllActivitiesManagerCompletion;
import io.keepcoding.madridshops.domain.managers.network.ManagerErrorCompletion;
import io.keepcoding.madridshops.domain.managers.network.NetworkManagerActivities;
import io.keepcoding.madridshops.domain.managers.network.entities.ActivityEntity;
import io.keepcoding.madridshops.domain.managers.network.mappers.ActivityEntityIntoActivitiesMapper;
import io.keepcoding.madridshops.domain.model.Activity.Activities;

public class GetAllActivitiesInteractorImpl implements GetAllActivitiesInteractor {

    private NetworkManagerActivities networkManager;

    public GetAllActivitiesInteractorImpl(@NonNull final NetworkManagerActivities networkManager) {
        this.networkManager = networkManager;
    }

    @Override
    public void execute(@NonNull final GetAllActivitiesInteractorCompletion completion, @Nullable final InteractorErrorCompletion onError) {
        if (this.networkManager == null) {
            if (onError == null) {
                throw new IllegalStateException("Network manager can't be null");
            } else {
                onError.onError("");
            }
        }

        this.networkManager.getActivitiesFromServer(new GetAllActivitiesManagerCompletion() {
            @Override
            public void completion(@NonNull List<ActivityEntity> activityEntityList) {
                Log.d("ACTIVITY", activityEntityList.toString());
                if (completion != null) {
                    Activities activities = ActivityEntityIntoActivitiesMapper.map(activityEntityList);
                    completion.completion(activities);
                }
            }
        }, new ManagerErrorCompletion() {
            @Override
            public void onError(String errorDescription) {
                if (onError != null) {
                    onError.onError(errorDescription);
                }
            }
        });
    }
}
