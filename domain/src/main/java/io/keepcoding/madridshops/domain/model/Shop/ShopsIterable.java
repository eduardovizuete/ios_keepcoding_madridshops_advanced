package io.keepcoding.madridshops.domain.model.Shop;

import java.util.List;

public interface ShopsIterable {
    long size();
    Shop get(long index);
    List<Shop> allShops();
}
