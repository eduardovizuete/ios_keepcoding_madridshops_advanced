package io.keepcoding.madridshops.domain.managers.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.WeakReference;
import java.util.List;

import io.keepcoding.domain.R;
import io.keepcoding.madridshops.domain.managers.network.entities.ActivityEntity;
import io.keepcoding.madridshops.domain.managers.network.jsonparser.ActivitiesJsonParser;

public class GetAllActivitiesManagerImpl implements NetworkManagerActivities {

    WeakReference<Context> weakContext;

    public GetAllActivitiesManagerImpl(Context context) {
        weakContext = new WeakReference<Context>(context);
    }

    @Override
    public void getActivitiesFromServer(@NonNull final GetAllActivitiesManagerCompletion completion, @Nullable final ManagerErrorCompletion errorCompletion) {
        String url = weakContext.get().getString(R.string.activities_url);
        RequestQueue queue = Volley.newRequestQueue(weakContext.get());

        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("JSON activities", response);

                        ActivitiesJsonParser parser = new ActivitiesJsonParser();
                        List<ActivityEntity> entities = parser.parse(response);

                        if (completion != null) {
                            completion.completion(entities);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", error.toString());
                        if (errorCompletion != null) {
                            errorCompletion.onError(error.getMessage());
                        }
                    }
                }
        );
        queue.add(request);
    }
}
