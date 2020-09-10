package com.gd.promo;

import com.gd.promo.model.CartItem;
import com.gd.promo.model.Item;
import com.gd.promo.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PromotionApplicationTests {
    @MockBean
    private ShoppingCartService cartService;

    @BeforeEach
    void setUp() {
        buildCartItems();
    }

    @Test
    void contextLoads() {
    }


    @Test
    public void calculateTotalOrderValue() {
        double scenarioAValue = OrderAndGetScenarioA();
        assertEquals(scenarioAValue, 100.0);
        double scenarioBValue = OrderAndGetScenarioB();
        assertEquals(scenarioBValue, 420.0);
        double scenarioCValue = OrderAndGetScenarioC();
        assertEquals(scenarioCValue, 335.0);
    }

    @Test
    public void calculateTotalOrderValueAfterPromotion() {
        double scenarioAValue = OrderAndGetScenarioA();
        assertEquals(scenarioAValue, 100.0);
        double scenarioBValue = OrderAndGetScenarioB();
        assertEquals(scenarioBValue, 370.0);
        double scenarioCValue = OrderAndGetScenarioC();
        assertEquals(scenarioCValue, 280.0);
    }

    private double OrderAndGetScenarioA() {
        return 0;
    }

    private double OrderAndGetScenarioC() {
        return 0;
    }

    private double OrderAndGetScenarioB() {
        return 0;
    }


    private void buildCartItems() {
        Item item = new Item();
        item.setSkuId("A");
        item.setName("AAAA");
        item.setPrice(50.0);
        cartService.addItemToStock(item);
        Item item2 = new Item();
        item2.setSkuId("B");
        item2.setName("BBB");
        item2.setPrice(30.0);
        cartService.addItemToStock(item2);
        Item item3 = new Item();
        item3.setSkuId("C");
        item3.setName("CCC");
        item3.setPrice(20.0);
        cartService.addItemToStock(item3);
        Item item4 = new Item();
        item4.setSkuId("D");
        item4.setName("DDD");
        item4.setPrice(15.0);
        cartService.addItemToStock(item4);
    }
}
