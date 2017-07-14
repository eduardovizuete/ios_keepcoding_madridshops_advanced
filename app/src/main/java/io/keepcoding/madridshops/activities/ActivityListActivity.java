package io.keepcoding.madridshops.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesFromCacheInteractor;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesFromCacheInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesInteractor;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesInteractorCompletion;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.GetIfAllActivitiesAreCachedInteractor;
import io.keepcoding.madridshops.domain.interactors.GetIfAllActivitiesAreCachedInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.InteractorErrorCompletion;
import io.keepcoding.madridshops.domain.interactors.SaveAllActivitiesIntoCacheInteractor;
import io.keepcoding.madridshops.domain.interactors.SaveAllActivitiesIntoCacheInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.SetAllActivitiesAreCachedInteractor;
import io.keepcoding.madridshops.domain.interactors.SetAllActivitiesAreCachedInteractorImpl;
import io.keepcoding.madridshops.domain.managers.cache.GetAllActivitiesFromCacheManager;
import io.keepcoding.madridshops.domain.managers.cache.GetAllActivitiesFromCacheManagerDAOImpl;
import io.keepcoding.madridshops.domain.managers.cache.SaveAllActivitiesIntoCacheManager;
import io.keepcoding.madridshops.domain.managers.cache.SaveAllActivitiesIntoCacheManagerDAOImpl;
import io.keepcoding.madridshops.domain.managers.network.GetAllActivitiesManagerImpl;
import io.keepcoding.madridshops.domain.managers.network.NetworkManagerActivities;
import io.keepcoding.madridshops.domain.model.Activity.Activities;
import io.keepcoding.madridshops.domain.model.Activity.Activity;
import io.keepcoding.madridshops.fragments.ActivitiesFragment;
import io.keepcoding.madridshops.navigator.Navigator;
import io.keepcoding.madridshops.views.OnElementClick;

public class ActivityListActivity extends AppCompatActivity {

    @BindView(R.id.ctivity_activity_list__progress_bar) ProgressBar progressBar;
    ActivitiesFragment activitiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        activitiesFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activity_list__fragment_activities);

        GetIfAllActivitiesAreCachedInteractor getIfAllActivitiesAreCachedInteractor = new GetIfAllActivitiesAreCachedInteractorImpl(getBaseContext());
        getIfAllActivitiesAreCachedInteractor.execute(new Runnable() {
            @Override
            public void run() {
                // all cached already, no need to download things, just read from DB
                readDataFromCache();
            }
        }, new Runnable() {
            @Override
            public void run() {
                // nothing cached yet
                obtainActivitiesList();
            }
        });
    }

    private void readDataFromCache() {
        GetAllActivitiesFromCacheManager getAllActivitiesFromCacheManager = new GetAllActivitiesFromCacheManagerDAOImpl(this);
        GetAllActivitiesFromCacheInteractor getAllActivitiesFromCacheInteractor = new GetAllActivitiesFromCacheInteractorImpl(getAllActivitiesFromCacheManager);
        getAllActivitiesFromCacheInteractor.execute(new GetAllActivitiesInteractorCompletion() {
            @Override
            public void completion(@NonNull Activities activities) {
                configActivitiesFragment(activities);
            }
        });
    }

    private void obtainActivitiesList() {
        progressBar.setVisibility(View.VISIBLE);

        NetworkManagerActivities manager = new GetAllActivitiesManagerImpl(this);

        GetAllActivitiesInteractor getAllActivitiesInteractor = new GetAllActivitiesInteractorImpl(manager);
        getAllActivitiesInteractor.execute(
                new GetAllActivitiesInteractorCompletion() {
                    @Override
                    public void completion(@NonNull final Activities activities) {
                        Log.i(this.getClass().getCanonicalName(), "Ejecutando getAllActivitiesInteractor");
                        // save into cache
                        SaveAllActivitiesIntoCacheManager saveManager = new SaveAllActivitiesIntoCacheManagerDAOImpl(getBaseContext());
                        SaveAllActivitiesIntoCacheInteractor saveInteractor = new SaveAllActivitiesIntoCacheInteractorImpl(saveManager);
                        saveInteractor.execute(activities, new Runnable() {
                            @Override
                            public void run() {
                                // set flag activities cached
                                SetAllActivitiesAreCachedInteractor setAllActivitiesAreCachedInteractor = new SetAllActivitiesAreCachedInteractorImpl(getBaseContext());
                                setAllActivitiesAreCachedInteractor.execute(true);
                            }
                          });

                        configActivitiesFragment(activities);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                },
                new InteractorErrorCompletion() {
                    @Override
                    public void onError(String errorDescription) {
                        Log.i(this.getClass().getCanonicalName(), "Error Ejecutando getAllActivitiesInteractor");
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
        );
    }

    private void configActivitiesFragment(final Activities activities) {
        activitiesFragment.setActivities(activities);
        activitiesFragment.setOnElementClickListener(new OnElementClick<Activity>() {
            @Override
            public void clickedOn(@NonNull Activity element, int position) {
                Navigator.navigateFromActivityListActivityToActivityDetailActivity(ActivityListActivity.this, element, position);
            }
        });
    }
}
