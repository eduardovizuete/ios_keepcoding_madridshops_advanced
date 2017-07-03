package io.keepcoding.madridshops.domain.model.Shop;

public interface ShopsUpdatable {
    void add(Shop shop);
    void delete(Shop shop);
    void update(Shop newShop, long index);
}
