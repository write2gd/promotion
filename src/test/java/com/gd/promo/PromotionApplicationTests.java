package com.gd.promo;

import com.gd.promo.model.CartItem;
import com.gd.promo.model.Item;
import com.gd.promo.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PromotionApplicationTests {
    @Autowired
    private ShoppingCartService cartService;

    @BeforeEach
    void setUp() {
        buildCartItems();
    }

    @Test
    void contextLoads() {
    }


    @Test
    public void calculateTotalOrderValueScenarioA() {
        double scenarioAValue = OrderAndGetScenarioA("sessionA");
        assertEquals(100.0, scenarioAValue);
    }
    @Test
    public void calculateTotalOrderValueScenarioB() {
        double scenarioBValue = OrderAndGetScenarioB("sessionB");
        assertEquals(420.0, scenarioBValue);
    }
    @Test
    public void calculateTotalOrderValueScenarioC() {
        double scenarioCValue = OrderAndGetScenarioC("sessionC");
        assertEquals(335.0, scenarioCValue);
    }

    @Test
    public void calculateTotalOrderValueAfterPromotionScenarioA() {
        double scenarioAValue = OrderAndGetAfterPromotionScenarioA("promotional_sessionA");
        assertEquals(100.0, scenarioAValue);
    }
    @Test
    public void calculateTotalOrderValueAfterPromotionScenarioB() {
        double scenarioBValue = OrderAndGetAfterPromotionScenarioB("promotional_sessionB");
        assertEquals(370.0, scenarioBValue);
    }
    @Test
    public void calculateTotalOrderValueAfterPromotionScenarioC() {
        double scenarioCValue = OrderAndGetAfterPromotionScenarioC("promotional_sessionC");
        assertEquals(280.0, scenarioCValue);
    }

    private double OrderAndGetAfterPromotionScenarioC(String sessionId) {
        Item A = cartService.getItem("A");
        cartService.orderItem(sessionId, A, 3);
        Item B = cartService.getItem("B");
        cartService.orderItem(sessionId, B, 5);
        Item C = cartService.getItem("C");
        cartService.orderItem(sessionId, C, 1);
        Item D = cartService.getItem("D");
        cartService.orderItem(sessionId, D, 1);
        return cartService.checkOut(sessionId);
    }

    private double OrderAndGetAfterPromotionScenarioB(String sessionId) {
        Item A = cartService.getItem("A");
        cartService.orderItem(sessionId, A, 5);
        Item B = cartService.getItem("B");
        cartService.orderItem(sessionId, B, 5);
        Item C = cartService.getItem("C");
        cartService.orderItem(sessionId, C, 1);
        return cartService.checkOut(sessionId);
    }

    private double OrderAndGetAfterPromotionScenarioA(String sessionId) {
        Item A = cartService.getItem("A");
        cartService.orderItem(sessionId, A, 1);
        Item B = cartService.getItem("B");
        cartService.orderItem(sessionId, B, 1);
        Item C = cartService.getItem("C");
        cartService.orderItem(sessionId, C, 1);

        return cartService.checkOut(sessionId);
    }

    private double OrderAndGetScenarioA(String sessionId) {
        Item A = cartService.getItem("A");
        cartService.orderItem(sessionId, A, 1);
        Item B = cartService.getItem("B");
        cartService.orderItem(sessionId, B, 1);
        Item C = cartService.getItem("C");
        cartService.orderItem(sessionId, C, 1);

        return cartService.calculateTotal(sessionId);
    }

    private double OrderAndGetScenarioC(String sessionId) {
        Item A = cartService.getItem("A");
        cartService.orderItem(sessionId, A, 3);
        Item B = cartService.getItem("B");
        cartService.orderItem(sessionId, B, 5);
        Item C = cartService.getItem("C");
        cartService.orderItem(sessionId, C, 1);
        Item D = cartService.getItem("D");
        cartService.orderItem(sessionId, D, 1);
        return cartService.calculateTotal(sessionId);
    }

    private double OrderAndGetScenarioB(String sessionId) {
        Item A = cartService.getItem("A");
        cartService.orderItem(sessionId, A, 5);
        Item B = cartService.getItem("B");
        cartService.orderItem(sessionId, B, 5);
        Item C = cartService.getItem("C");
        cartService.orderItem(sessionId, C, 1);

        return cartService.calculateTotal(sessionId);
    }


    private void buildCartItems() {
        Item item = new Item();
        item.setSkuId("A");
        item.setName("AAAA");
        item.setPromotionId(2l);
        item.setPrice(50.0);
        cartService.addItemToStock(item);
        Item item2 = new Item();
        item2.setSkuId("B");
        item2.setName("BBB");
        item2.setPromotionId(3l);
        item2.setPrice(30.0);
        cartService.addItemToStock(item2);
        Item item3 = new Item();
        item3.setSkuId("C");
        item3.setName("CCC");
        item3.setPromotionId(4l);
        item3.setPrice(20.0);
        cartService.addItemToStock(item3);
        Item item4 = new Item();
        item4.setSkuId("D");
        item4.setName("DDD");
        item4.setPromotionId(4l);
        item4.setPrice(15.0);
        cartService.addItemToStock(item4);
    }
}
