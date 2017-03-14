package com.example.shopping.service;

import com.example.shopping.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kumar k on 14/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartServiceRemoteTest {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Test
    public void checkoutEmpty() throws Exception {
        Map<Product,Integer> emptyMap = new LinkedHashMap<>();
        BigDecimal actualCheckoutPrice = shoppingCartService.checkout(emptyMap);
        assertThat(actualCheckoutPrice).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void checkoutAppleProducts() throws Exception{
        Product apple = new Product.Builder().name("APPLE").price(new BigDecimal(0.60)).build();
        Map<Product, Integer> itemMap = new LinkedHashMap<>();
        itemMap.put(apple,4);

        BigDecimal actualCheckoutPrice = shoppingCartService.checkout(itemMap);
        BigDecimal calculateApple = apple.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(2));
        assertThat(actualCheckoutPrice).isEqualTo(calculateApple);
    }

    @Test
    public void checkoutOrangeProducts() throws Exception{
        Product orange = new Product.Builder().name("ORANGE").price(new BigDecimal(0.25)).build();
        Map<Product, Integer> itemMap = new LinkedHashMap<>();
        itemMap.put(orange,6);

        BigDecimal actualCheckoutPrice = shoppingCartService.checkout(itemMap);
        BigDecimal calculateOrange = orange.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(4));
        assertThat(actualCheckoutPrice).isEqualTo(calculateOrange);
    }

    @Test
    public void checkoutProducts() throws Exception{
        Product apple = new Product.Builder().name("APPLE").price(new BigDecimal(0.60)).build();
        Product orange = new Product.Builder().name("ORANGE").price(new BigDecimal(0.25)).build();
        Map<Product, Integer> itemMap = new LinkedHashMap<>();
        itemMap.put(apple,4);
        itemMap.put(orange,6);

        BigDecimal actualCheckoutPrice = shoppingCartService.checkout(itemMap);
        BigDecimal calculateApple = apple.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(2));
        BigDecimal calculateOrange = orange.getPrice().setScale(2,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(4));
        assertThat(actualCheckoutPrice).isEqualTo(calculateApple.add(calculateOrange));
    }
}
