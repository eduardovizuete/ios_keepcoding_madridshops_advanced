package io.keepcoding.madridshops.util.map.model;

import java.util.ArrayList;
import java.util.List;

import io.keepcoding.madridshops.domain.model.Activity.Activities;
import io.keepcoding.madridshops.domain.model.Activity.Activity;
import io.keepcoding.madridshops.util.map.MapPinnable;

public class ActivityPin implements MapPinnable<Activity> {

    private Activity activity;

    public static List<MapPinnable> activitiesPinsFromActivities(Activities activities) {
        List<Activity> activityList = activities.allActivities();
        List<MapPinnable> activityPinList = new ArrayList<>();

        for (Activity activity : activityList) {
            ActivityPin ap = new ActivityPin(activity);
            activityPinList.add(ap);
        }

        return activityPinList;
    }

    public ActivityPin(Activity activity) {
        this.activity = activity;
    }

    @Override
    public float getLatitude() {
        return activity.getLatitude();
    }

    @Override
    public float getLongitude() {
        return activity.getLongitude();
    }

    @Override
    public String getPinDescription() {
        return activity.getName() + " - " + activity.getAddress();
    }

    @Override
    public String getPinImageUrl() {
        return activity.getImageUrl();
    }

    @Override
    public Activity getRelatedModelObject() {
        return activity;
    }
}
