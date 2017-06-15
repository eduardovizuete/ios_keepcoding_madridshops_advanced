package io.keepcoding.madridshops.domain.model;

import java.util.List;

public class Shops implements ShopsIterable, ShopsUpdatable {
    @Override
    public long size() {
        return 0;
    }

    @Override
    public void add(Shop shop) {

    }

    @Override
    public Shop get(long index) {
        return null;
    }

    @Override
    public void delete(Shop shop) {

    }

    @Override
    public List<Shop> allShops() {
        return null;
    }

    @Override
    public void update(Shop newShop, long index) {

    }
}
