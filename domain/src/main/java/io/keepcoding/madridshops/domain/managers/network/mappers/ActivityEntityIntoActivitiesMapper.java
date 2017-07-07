package io.keepcoding.madridshops.domain.managers.network.mappers;

import android.util.Log;

import java.util.List;

import io.keepcoding.madridshops.domain.managers.network.entities.ActivityEntity;
import io.keepcoding.madridshops.domain.model.Activity.Activities;
import io.keepcoding.madridshops.domain.model.Activity.Activity;

public class ActivityEntityIntoActivitiesMapper {

    /**
     *
     * @param activityEntities
     * @return null if activityEntities is null or activityEntities is empty else a Activities aggregate
     */
    public static Activities map(final List<ActivityEntity> activityEntities) {
        Activities activities = new Activities();

        for (ActivityEntity activityEntity : activityEntities) {
            Activity activity = Activity.of(activityEntity.getId(), activityEntity.getName());

            activity.setDescription_es(activityEntity.getDescription_es());
            activity.setDescription_en(activityEntity.getDescription_en());
            activity.setAddress(activityEntity.getAddress());
            activity.setUrl(activityEntity.getUrl());
            activity.setImageUrl(activityEntity.getImageUrl());
            activity.setLogoUrl(activityEntity.getLogoUrl());
            activity.setLatitude(getCorrectCoordinateComponent(activityEntity.getLatitude()));
            activity.setLongitude(getCorrectCoordinateComponent(activityEntity.getLongitude()));

            activities.add(activity);
        }

        return activities;
    }

    public static float getCorrectCoordinateComponent(String coordinateComponent) {
        // problem: JSON has this errors "-3.9018104,275"
        float coordinate = 0.0f;

        String s = coordinateComponent.replace(",", "");
        try {
            coordinate = Float.parseFloat(s);
        } catch (Exception e) {
            Log.d("ERROR CONVERTING", String.format("Can't convert %s", coordinateComponent));
        }
        return coordinate;
    }

}

