package com.gd.promo.service;

import com.gd.promo.dao.PromotionDao;
import com.gd.promo.helper.PromotionHelper;
import com.gd.promo.model.CartItem;
import com.gd.promo.model.Item;
import com.gd.promo.model.Promotion;
import com.gd.promo.rule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final Map<String, Item> stocks = new HashMap<>();
    private static final Map<String, List<CartItem>> cart = new HashMap<>();


    @Autowired
    private PromotionDao promotionDao;
    @Autowired
    private CombinationPromotion combinationPromotion;
    @Autowired
    private PricePromotion pricePromotion;
    @Autowired
    private QuantityPromotion quantityPromotion;
    @Autowired
    private DefaultPromotion defaultPromotion;

    @Override
    public void addItemToStock(Item item) {
        stocks.put(item.getSkuId(), item);
    }

    @Override
    public Item getItem(String skuId) {
        return stocks.get(skuId);
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
        return cart.get(sessionId).stream().mapToDouble(item -> item.getQuantity() * item.getItem().getPrice()).sum();
    }

    @Override
    public double checkOut(String sessionId) {
        List<Promotion> promotionList = promotionDao.getAllPromotion();
        double total = applyPromotions(promotionList, cart.get(sessionId));
        return total;
    }

    private double applyPromotions(List<Promotion> promotionList, List<CartItem> cartItems) {
        Double total = 0.0;
        Iterator<CartItem> cartItemIterator = cartItems.iterator();
        while (cartItemIterator.hasNext()) {
            CartItem cartItem = cartItemIterator.next();
            Promotion promotionToApply = PromotionHelper.findPromotion(cartItem, promotionList);
            total += promotionApplier(promotionToApply).apply(promotionToApply, cartItem, cartItems);
        }
        return total.doubleValue();
    }

    private PromotionCalculator promotionApplier(Promotion promotionToApply) {
        if (promotionToApply.getRules().size() == 2) {
            return pricePromotion;
        } else if (promotionToApply.getRules().size() == 3) {
            return quantityPromotion;
        } else if (promotionToApply.getRules().size() >= 5) {
            return combinationPromotion;
        } else {
            return defaultPromotion;
        }
    }

}
