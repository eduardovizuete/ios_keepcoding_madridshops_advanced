package io.keepcoding.madridshops.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesInteractor;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesInteractorCompletion;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesInteractorFakeImpl;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.InteractorErrorCompletion;
import io.keepcoding.madridshops.domain.managers.network.GetAllActivitiesManagerImpl;
import io.keepcoding.madridshops.domain.managers.network.NetworkManagerActivities;
import io.keepcoding.madridshops.domain.model.Activity.Activities;
import io.keepcoding.madridshops.domain.model.Activity.Activity;
import io.keepcoding.madridshops.fragments.ActivitiesFragment;

public class ActivityListActivity extends AppCompatActivity {

    ActivitiesFragment activitiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        activitiesFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activity_list__fragment_activities);

        // obtain activities list
        NetworkManagerActivities manager = new GetAllActivitiesManagerImpl(getApplicationContext());

        GetAllActivitiesInteractor getAllActivitiesInteractor = new GetAllActivitiesInteractorImpl(manager);
        getAllActivitiesInteractor.execute(
                new GetAllActivitiesInteractorCompletion() {
                    @Override
                    public void completion(@NonNull Activities activities) {
                        Log.i(this.getClass().getCanonicalName(), "Ejecutando getAllActivitiesInteractor");
                        activitiesFragment.setActivities(activities);
                    }
                },
                new InteractorErrorCompletion() {
                    @Override
                    public void onError(String errorDescription) {
                        Log.i(this.getClass().getCanonicalName(), "Error Ejecutando getAllActivitiesInteractor");
                    }
                });
    }
}
