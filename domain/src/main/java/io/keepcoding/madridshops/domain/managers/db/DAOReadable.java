package io.keepcoding.madridshops.domain.managers.db;

import android.support.annotation.Nullable;

import java.util.List;

import io.keepcoding.madridshops.domain.model.Shop;

public interface DAOReadable<T> {
    T query(final long id);
    List<T> query();
    @Nullable List<T> query(String where, String[] whereArgs, String orderBy);
    int numRecords();
}
