package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.keepcoding.madridshops.domain.model.Activity.Activities;
import io.keepcoding.madridshops.domain.model.Activity.Activity;
import io.keepcoding.madridshops.domain.model.Shop.Shop;

public class GetAllActivitiesInteractorFakeImpl implements GetAllActivitiesInteractor {
    @Override
    public void execute(@NonNull GetAllActivitiesInteractorCompletion completion, @Nullable InteractorErrorCompletion onError) {
        Activities activities = new Activities();

        for (int i = 0; i < 10; i++) {
            Activity activity = Activity.of(i, "My Activity " + i);
            activities.add(activity);
        }

        if (completion != null) {
            completion.completion(activities);
        }
    }
}
