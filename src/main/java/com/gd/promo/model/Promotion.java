package com.gd.promo.model;

import java.util.ArrayList;
import java.util.List;

public class Promotion {
    private Long promotionId;
    private String name;
    private List<String> rules = new ArrayList<>();
    private PromotionState status;

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRules() {
        return rules;
    }

    public PromotionState getStatus() {
        return status;
    }

    public void setStatus(PromotionState status) {
        this.status = status;
    }
}
