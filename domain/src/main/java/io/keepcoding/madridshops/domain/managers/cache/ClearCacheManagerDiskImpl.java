package io.keepcoding.madridshops.domain.managers.cache;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

import io.keepcoding.domain.R;

import static android.R.attr.path;

public class ClearCacheManagerDiskImpl implements ClearCacheManager {
    private WeakReference<Context> contextWeakReference;

    public ClearCacheManagerDiskImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<>(contextWeakReference);
    }

    @Override
    public void execute(Runnable completion) {
        // delete cache directory
        String cacheDiskDir = this.contextWeakReference.get().getCacheDir().getPath() + "/" +
                this.contextWeakReference.get().getString(R.string.app_name_cachefiles) + "/";

        File file = new File(cacheDiskDir);

        if (file.exists()) {
            String deleteCmd = "rm -r " + cacheDiskDir;
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(deleteCmd);
            } catch (IOException e) {
                Log.e("IOException", e.getLocalizedMessage());
            }
        }
    }
}
