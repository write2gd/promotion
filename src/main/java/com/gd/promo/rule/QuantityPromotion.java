package com.gd.promo.rule;

import com.gd.promo.model.CartItem;
import com.gd.promo.model.Promotion;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component(value = "QuantityPromotion")
public class QuantityPromotion implements PromotionCalculator {
    @Override
    public Double apply(Promotion promotionToApply, CartItem cartItem, List<CartItem> cartItems) {
        List<String> existingRule = promotionToApply.getRules();
        Double decimalPrice = 0.0;
        Integer quantity = Integer.parseInt(existingRule.get(0));
        String item = existingRule.get(1);
        Double promotionPrice = Double.parseDouble(existingRule.get(2));
        decimalPrice += applyPromotion(cartItem, decimalPrice, item, quantity, promotionPrice);
        decimalPrice += cartItem.getQuantity() * cartItem.getItem().getPrice();
        return decimalPrice;
    }

    private Double applyPromotion(CartItem cartItem, Double decimalPrice, String item, Integer quantity, Double promotionPrice) {
        if (cartItem.getQuantity() >= quantity) {
            decimalPrice += promotionPrice;
            cartItem.setQuantity(cartItem.getQuantity() - quantity);
        }
        if (cartItem.getQuantity() - quantity > 0) {
            return applyPromotion(cartItem, decimalPrice, item, quantity, promotionPrice);
        }
        return decimalPrice;
    }
}
