package com.gd.promo.service;


import com.gd.promo.model.Item;

public interface ShoppingCartService {

    void addItemToStock(Item item);

    Item getItem(String skuId);

    void orderItem(String sessionId, Item item, int quantity);

    double calculateTotal(String sessionId);
}
