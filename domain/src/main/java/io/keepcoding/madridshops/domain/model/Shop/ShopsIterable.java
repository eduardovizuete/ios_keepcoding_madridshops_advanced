package io.keepcoding.madridshops.domain.model.Shop;

import java.util.List;

import io.keepcoding.madridshops.domain.model.Shop.Shop;

public interface ShopsIterable {
    long size();
    Shop get(long index);
    List<Shop> allShops();
}
