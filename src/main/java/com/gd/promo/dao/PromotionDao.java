package com.gd.promo.dao;

import com.gd.promo.model.Promotion;
import com.gd.promo.model.PromotionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PromotionDao {
    private static final List<Promotion> promotionList = new ArrayList<>();

    @Autowired
    private Environment env;

    public List<Promotion> getAllPromotion() {
        if (promotionList.size() > 0) {
            return promotionList;
        }
        int i = 1;
        while (env.getProperty("promo." + i) != null) {
            String[] promoExpression = env.getProperty("promo." + i).split(",");
            promotionList.add(populatePromoEntity(promoExpression));
            i++;
        }
        return promotionList;
    }

    private Promotion populatePromoEntity(String[] promoExpression) {
        Promotion promotion = new Promotion();
        promotion.setPromotionId(Long.valueOf(promoExpression[0]));
        promotion.setName(promoExpression[1]);
        promotion.setStatus(PromotionState.valueOf(promoExpression[2]));
        for (int i = 3; i < promoExpression.length; i++) {
            promotion.getRules().add(promoExpression[i]);
        }

        return promotion;
    }
}
