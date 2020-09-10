package com.gd.promo.service;

import com.gd.promo.model.CartItem;
import com.gd.promo.model.Item;
import com.gd.promo.model.Promotion;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final Map<String, Item> stocks = new HashMap<>();
    private static final Map<String, List<CartItem>> cart = new HashMap<>();

    @Override
    public void addItemToStock(Item item) {
        stocks.put(item.getSkuId(), item);
    }

    @Override
    public Item getItem(String skuId) {
        return null;
    }

    @Override
    public void orderItem(String sessionId, Item item, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(quantity);
        cart.putIfAbsent(sessionId, new ArrayList<>());
        cart.get(sessionId).add(cartItem);

    }

    @Override
    public double calculateTotal(String sessionId) {
        return 0;
    }


}
