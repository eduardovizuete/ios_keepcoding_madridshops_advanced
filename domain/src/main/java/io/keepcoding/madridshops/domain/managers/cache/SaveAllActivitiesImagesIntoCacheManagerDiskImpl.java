package io.keepcoding.madridshops.domain.managers.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

import io.keepcoding.domain.R;
import io.keepcoding.madridshops.domain.model.Activity.Activities;
import io.keepcoding.madridshops.domain.model.Activity.Activity;

public class SaveAllActivitiesImagesIntoCacheManagerDiskImpl implements SaveAllActivitiesIntoCacheManager {
    private static WeakReference<Context> contextWeakReference;
    private static String cacheDiskDir;

    public SaveAllActivitiesImagesIntoCacheManagerDiskImpl(Context contextWeakReference) {
        SaveAllActivitiesImagesIntoCacheManagerDiskImpl.contextWeakReference = new WeakReference<>(contextWeakReference);
        cacheDiskDir = SaveAllActivitiesImagesIntoCacheManagerDiskImpl.contextWeakReference.get().getString(R.string.app_name_cachefiles);
    }

    @Override
    public void execute(Activities activities, Runnable completion) {
        createCacheDiskDir(contextWeakReference.get().getCacheDir().getPath() + "/" + cacheDiskDir);
        for (Activity activity : activities.allActivities()) {
            if (activity.getImageUrl() != null || activity.getImageUrl() != "") {
                String partOfUrl = activity.getImageUrl().substring(activity.getImageUrl().lastIndexOf("/"), activity.getImageUrl().length());

                Picasso.with(contextWeakReference.get())
                        .load(activity.getImageUrl())
                        .into(getTarget(partOfUrl));
            }

        }

        completion.run();
    }

    private void createCacheDiskDir(String dir) {
            File createDir = new File(dir);
            createDir.mkdirs();
    }

    //target to save
    private static Target getTarget(final String url){
        Target target = new Target(){
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File file = new File(contextWeakReference.get().getCacheDir().getPath() + "/" + cacheDiskDir + "/" + url);
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                            ostream.flush();
                            ostream.close();
                        } catch (IOException e) {
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }
}
