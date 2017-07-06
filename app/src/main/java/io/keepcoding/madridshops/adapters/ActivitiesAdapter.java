package io.keepcoding.madridshops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.model.Activity.Activities;
import io.keepcoding.madridshops.domain.model.Activity.Activity;
import io.keepcoding.madridshops.views.ActivityRowViewHolder;
import io.keepcoding.madridshops.views.OnElementClick;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivityRowViewHolder> {

    private Activities activities;
    private LayoutInflater inflater;
    private OnElementClick<Activity> listener;

    public ActivitiesAdapter(final Activities activities, final Context context) {
        this.activities = activities;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ActivityRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_activity, parent, false);
        ActivityRowViewHolder viewHolder = new ActivityRowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ActivityRowViewHolder activityRow, final int position) {
        final Activity activity = this.activities.get(position);
        activityRow.setActivity(activity);

        activityRow.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.clickedOn(activity, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.activities != null) {
            return (int) this.activities.size();
        }
        return 0;
    }

    public void setOnClickListener(OnElementClick<Activity> listener) {
        this.listener = listener;
    }
}
