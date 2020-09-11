package com.gd.promo.rule;

import com.gd.promo.model.CartItem;
import com.gd.promo.model.Item;
import com.gd.promo.model.Promotion;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component(value = "CombinationPromotion")
public class CombinationPromotion implements PromotionCalculator {

    @Override
    public Double apply(Promotion promotionToApply, CartItem cartItem, List<CartItem> cartItems) {
        Map<String, Integer> combineItems = new HashMap<>();
        List<String> existingRule = promotionToApply.getRules();
        Double decimalPrice = 0.0;
        Double promotionPrice = Double.parseDouble(existingRule.get((existingRule.size() - 1)));
        if (combineItems.size() == 0) {
            for (int i = 0; i < existingRule.size(); i++) {
                if (i % 2 != 0) {
                    combineItems.put(existingRule.get(i), Integer.valueOf(existingRule.get(i - 1)));
                }
            }
        }
        decimalPrice += applyPromotion(cartItems, combineItems, decimalPrice, promotionPrice);
        Iterator<CartItem> cartItemIterator = cartItems.iterator();
        while (cartItemIterator.hasNext()) {
            CartItem item = cartItemIterator.next();
            if (combineItems.containsKey(item.getItem().getSkuId()) && !item.getItem().isPromotionApplied()) {
                decimalPrice += item.getQuantity() * item.getItem().getPrice();
                item.getItem().setPromotionApplied(true);
            }
        }


        return decimalPrice;
    }

    private Double applyPromotion(List<CartItem> cartItems, Map<String, Integer> combineItems, Double decimalPrice, Double promoPrice) {
        int itemMatched = 0;
        for (CartItem item : cartItems) {
            if (combineItems.get(item.getItem().getSkuId()) != null && combineItems.get(item.getItem().getSkuId()) <= item.getQuantity()) {
                itemMatched++;
            }
        }
        if (combineItems.size() == itemMatched) {
            for (CartItem item : cartItems) {
                if (combineItems.containsKey(item.getItem().getSkuId())) {
                    item.setQuantity(item.getQuantity() - combineItems.get(item.getItem().getSkuId()));
                }
            }
            decimalPrice += promoPrice;
            return applyPromotion(cartItems, combineItems, decimalPrice, promoPrice);
        }
        return decimalPrice;
    }
}
