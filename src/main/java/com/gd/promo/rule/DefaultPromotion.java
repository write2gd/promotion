package com.gd.promo.rule;

import com.gd.promo.model.CartItem;
import com.gd.promo.model.Promotion;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component(value = "DefaultPromotion")
public class DefaultPromotion implements PromotionCalculator {

    @Override
    public Double apply(Promotion promotionToApply, CartItem cartItem, List<CartItem> cartItems) {
        return cartItem.getQuantity() * cartItem.getItem().getPrice();
    }
}
