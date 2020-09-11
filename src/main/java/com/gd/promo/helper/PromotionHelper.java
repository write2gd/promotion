package com.gd.promo.helper;

import com.gd.promo.model.CartItem;
import com.gd.promo.model.Promotion;

import java.util.List;

public class PromotionHelper {
    public static Promotion findPromotion(CartItem cartItem, List<Promotion> promotionList) {
        return promotionList.stream().filter(promotion -> promotion.getPromotionId() == cartItem.getItem().getPromotionId()).findAny().get();
    }
}
