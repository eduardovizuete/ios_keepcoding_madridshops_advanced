package io.keepcoding.madridshops.domain.managers.db;

import java.util.List;

public interface DAOReadable<T> {
    T query(final long id);
    List<T> query();
}
