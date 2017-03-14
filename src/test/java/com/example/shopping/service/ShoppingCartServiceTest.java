package com.example.shopping.service;

import com.example.shopping.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.when;

/**
 * Created by kumar k on 14/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceTest {

    @Mock
    ShoppingCartService shoppingCartService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTotalPrice() {
        Product apple = new Product.Builder().name("APPLE").price(new BigDecimal(0.60)).build();
        Map<Product, Integer> itemMap = new LinkedHashMap<>();
        itemMap.put(apple,2);
        when(shoppingCartService.checkout(anyMap())).thenReturn(apple.getPrice().multiply(BigDecimal.valueOf(2)));
        assertNotNull(shoppingCartService.checkout(itemMap));
    }

}
