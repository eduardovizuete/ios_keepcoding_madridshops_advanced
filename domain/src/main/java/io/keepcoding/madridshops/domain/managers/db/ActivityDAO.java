package io.keepcoding.madridshops.domain.managers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import io.keepcoding.madridshops.domain.model.Activity.Activity;

import static io.keepcoding.madridshops.domain.managers.db.DBConstants.*;

public class ActivityDAO implements DAOReadable<Activity>, DAOWritable<Activity>{
    private static final long EMPTY_ACTIVITY = 0;

    private SQLiteDatabase dbReadConnection;
    private SQLiteDatabase dbWriteConnection;

    public ActivityDAO(DBHelper dbHelper) {
        dbReadConnection = dbHelper.getReadableDatabase();
        dbWriteConnection = dbHelper.getWritableDatabase();
    }

    public ActivityDAO(Context context) {
        this(new DBHelper(context));
    }

    @Nullable
    @Override
    public List<Activity> query(String where, String[] whereArgs, String orderBy) {
        Cursor c = dbReadConnection.query(TABLE_ACTIVITY, // table name
                ALL_COLUMNS_TABLE_ACTIVITY,    // columns
                where,          // where
                whereArgs,      // where args
                orderBy,        // order by
                null,           // group by
                null);          // having

        if (c == null || c.getCount() == 0) {
            return null;
        }

        List<Activity> activityList = new ArrayList<>();

        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ID));
            String name = c.getString(c.getColumnIndex(KEY_ACTIVITY_NAME));
            String descriptionEs = c.getString(c.getColumnIndex(KEY_ACTIVITY_DESCRIPTION_ES));
            String descriptionEn = c.getString(c.getColumnIndex(KEY_ACTIVITY_DESCRIPTION_EN));
            String address = c.getString(c.getColumnIndex(KEY_ACTIVITY_ADDRESS));
            String url = c.getString(c.getColumnIndex(KEY_ACTIVITY_URL));
            String imageUrl = c.getString(c.getColumnIndex(KEY_ACTIVITY_IMAGE_URL));
            String logoUrl = c.getString(c.getColumnIndex(KEY_ACTIVITY_LOGO_URL));
            float latitude = c.getFloat(c.getColumnIndex(KEY_ACTIVITY_LATITUDE));
            float longitude = c.getFloat(c.getColumnIndex(KEY_ACTIVITY_LONGITUDE));

            Activity activity = Activity.of(id, name).
                    setDescription_es(descriptionEs).
                    setDescription_en(descriptionEn).
                    setAddress(address).
                    setUrl(url).
                    setImageUrl(imageUrl).
                    setLogoUrl(logoUrl).
                    setLatitude(latitude).
                    setLongitude(longitude);
            activityList.add(activity);
        }

        return activityList;
    }

    @Override
    public Activity query(long id) {
        String idAsString = String.format("%d", id);
        List<Activity> activities = query(KEY_ACTIVITY_ID + " = ?", new String[]{ idAsString }, KEY_ACTIVITY_ID);

        if (activities == null || activities.size() == 0) {
            return null;
        }
        return activities.get(0);
    }

    @Override
    public List<Activity> query() {
        return query(null, null, KEY_ACTIVITY_ID);
    }

    @Override
    public long insert(@NonNull Activity element) {
        if (element == null) {
            return EMPTY_ACTIVITY;
        }

        dbWriteConnection.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = dbWriteConnection.insert(TABLE_ACTIVITY, null, getContentValues(element));
            element.setId(id);

            dbWriteConnection.setTransactionSuccessful();
        } finally {
            dbWriteConnection.endTransaction();
        }

        return id;
    }

    private ContentValues getContentValues(Activity activity) {
        final ContentValues contentValues = new ContentValues();

        if (activity == null) {
            return contentValues;
        }

        contentValues.put(KEY_ACTIVITY_NAME, activity.getName());
        contentValues.put(KEY_ACTIVITY_DESCRIPTION_ES, activity.getDescription_es());
        contentValues.put(KEY_ACTIVITY_DESCRIPTION_ES, activity.getDescription_es());
        contentValues.put(KEY_ACTIVITY_ADDRESS, activity.getAddress());
        contentValues.put(KEY_ACTIVITY_URL, activity.getUrl());
        contentValues.put(KEY_ACTIVITY_IMAGE_URL, activity.getImageUrl());
        contentValues.put(KEY_ACTIVITY_LOGO_URL, activity.getLogoUrl());
        contentValues.put(KEY_ACTIVITY_LATITUDE, activity.getLatitude());
        contentValues.put(KEY_ACTIVITY_LONGITUDE, activity.getLongitude());

        return contentValues;
    }

    @Override
    public long update(long id, Activity element) {
        return 0;
    }

    @Override
    public long delete(long id) {
        return delete(KEY_ACTIVITY_ID + " = ?", "" + id);
    }

    @Override
    public long delete(Activity element) {
        return delete(element.getId());
    }

    @Override
    public void deleteAll() {
        delete(null, null);
    }

    @Override
    public long delete(String where, String... whereClause) {
        int deletedRegs = 0;
        dbWriteConnection.beginTransaction();
        try {
            deletedRegs = dbWriteConnection.delete(TABLE_ACTIVITY, where, whereClause);

            dbWriteConnection.setTransactionSuccessful();
        } finally {
            dbWriteConnection.endTransaction();
        }
        return deletedRegs;
    }

    @Override
    public int numRecords() {
        List<Activity> activitiesList = query();

        return activitiesList == null ? 0 : activitiesList.size();
    }
}
