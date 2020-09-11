package com.gd.promo.rule;

import com.gd.promo.model.CartItem;
import com.gd.promo.model.Promotion;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Component(value = "PricePromotion")
public class PricePromotion implements PromotionCalculator {
    @Override
    public Double apply(Promotion promotionToApply, CartItem cartItem, List<CartItem> cartItems) {
        List<String> existingRule = promotionToApply.getRules();
        Double decimalPrice = 0.0;
        Double minimumQuantity = Double.parseDouble(existingRule.get(0));
        int percentageDiscount = 100 - Integer.valueOf(existingRule.get(1));

        Iterator<CartItem> cartItemIterator = cartItems.iterator();
        while (cartItemIterator.hasNext()) {
            CartItem item = cartItemIterator.next();
            decimalPrice += item.getQuantity() * item.getItem().getPrice();
        }
        if (decimalPrice > minimumQuantity) {
            decimalPrice = decimalPrice * (percentageDiscount / 100);
        }
        return decimalPrice;
    }
}
