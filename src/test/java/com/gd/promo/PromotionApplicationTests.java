package com.gd.promo;

import com.gd.promo.model.Item;
import com.gd.promo.service.ShoppingCartService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class PromotionApplicationTests {
    @MockBean
    private ShoppingCartService cartService;

    @Test
    void contextLoads() {
        buildCartItems();
    }

    private void buildCartItems() {

    }

    @Test
    public void calculateTotalOrderValue() {
    }

    @Test
    public void calculateTotalOrderValueAfterPromotion() {
    }

}
