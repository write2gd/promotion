package com.gd.promo.rule;

import com.gd.promo.model.CartItem;
import com.gd.promo.model.Promotion;

import java.math.BigDecimal;
import java.util.List;

public interface PromotionCalculator {
    Double apply(Promotion promotionToApply, CartItem cartItem, List<CartItem> cartItems);
}
