package com.gd.promo.model;

public class Promotion {
    private Long promotionId;
    private String name;
    private String rule;
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

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public PromotionState getStatus() {
        return status;
    }

    public void setStatus(PromotionState status) {
        this.status = status;
    }
}
