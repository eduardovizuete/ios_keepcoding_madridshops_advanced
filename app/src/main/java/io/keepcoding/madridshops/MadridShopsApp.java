package io.keepcoding.madridshops;

import android.app.AlarmManager;
import android.app.Application;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.squareup.picasso.Picasso;

import io.keepcoding.madridshops.activities.ShopsListActivity;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractor;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.InteractorErrorCompletion;
import io.keepcoding.madridshops.domain.managers.network.GetAllShopsManagerCompletion;
import io.keepcoding.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import io.keepcoding.madridshops.domain.managers.network.ManagerErrorCompletion;
import io.keepcoding.madridshops.domain.managers.network.NetworkManager;
import io.keepcoding.madridshops.domain.model.Shops;
import io.keepcoding.madridshops.services.ShopService;

public class MadridShopsApp extends MultiDexApplication {

    private final String APP_NAME = MadridShopsApp.class.getCanonicalName();

    @Override
    public void onCreate() {
        super.onCreate();

        // init app
        Log.d(APP_NAME, "App starting" + BuildConfig.BASE_URL);

        //Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

        ShopService.startRunningService(this);

        Resources resources = getResources();
        Intent intent = new Intent(this, ShopsListActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Ticker")    // Set the "ticker" text which is sent to accessibility services.
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("You're spyed now")
                .setContentText("Notification Text")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        int notificationId = 0;
        notificationManager.notify(notificationId, notification);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        // low memory: dump something
    }
}
