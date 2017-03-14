package com.example.shopping.service;

import com.example.shopping.domain.Product;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by kumar on 14/03/2017.
 */
public interface ShoppingCartService {
    BigDecimal checkout(Map<Product,Integer> itemsMap);
}
