package io.keepcoding.madridshops.domain.managers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.List;

import io.keepcoding.madridshops.domain.model.Shop;

import static io.keepcoding.madridshops.domain.managers.db.DBConstants.*;

public class ShopDAO implements DAOReadable<Shop>, DAOWritable<Shop> {

    private static final long EMPTY_SHOP = 0;
    private SQLiteDatabase dbReadConnection;
    private SQLiteDatabase dbWriteConnection;

    public ShopDAO(DBHelper dbHelper) {
        dbWriteConnection = dbHelper.getWritableDatabase();
        dbReadConnection = dbHelper.getReadableDatabase();
    }

    public ShopDAO(Context context) {
        this(new DBHelper(context));
    }

    @Override
    public Shop query(long id) {
        return null;
    }

    @Override
    public List<Shop> query() {
        return null;
    }

    @Override
    public long insert(@NonNull Shop element) {
        if (element == null) {
            return EMPTY_SHOP;
        }

        dbWriteConnection.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id =dbWriteConnection.insert(DBConstants.TABLE_SHOP, null, getContenValues(element));
            element.setId(id);

            dbWriteConnection.setTransactionSuccessful();
        } finally {
            dbWriteConnection.endTransaction();
        }

        return id;
    }

    private ContentValues getContenValues(Shop shop) {
        final ContentValues contentValues = new ContentValues();

        if (shop == null) {
            return contentValues;
        }

        contentValues.put(KEY_SHOP_NAME, shop.getName());
        contentValues.put(KEY_SHOP_ADDRESS, shop.getAddress());
        contentValues.put(KEY_SHOP_DESCRIPTION, shop.getDescription());
        contentValues.put(KEY_SHOP_IMAGE_URL, shop.getImageUrl());
        contentValues.put(KEY_SHOP_LOGO_IMAGE_URL, shop.getLogoUrl());
        contentValues.put(KEY_SHOP_URL, shop.getUrl());
        contentValues.put(KEY_SHOP_LATITUDE, shop.getLatitude());
        contentValues.put(KEY_SHOP_LONGITUDE, shop.getLongitude());

        return contentValues;
    }

    @Override
    public long update(long id, Shop element) {
        return 0;
    }

    @Override
    public long delete(long id) {
        return 0;
    }

    @Override
    public long delete(Shop element) {
        return 0;
    }

    @Override
    public long deleteAll() {
        return 0;
    }
}
