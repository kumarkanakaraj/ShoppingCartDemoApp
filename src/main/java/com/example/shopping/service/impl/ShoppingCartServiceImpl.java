package com.example.shopping.service.impl;

import com.example.shopping.domain.AppleDiscount;
import com.example.shopping.domain.OrangeDiscount;
import com.example.shopping.domain.Product;
import com.example.shopping.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by kumar k on 14/03/2017.
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Autowired
    AppleDiscount appleDiscount;
    @Autowired
    OrangeDiscount orangeDiscount;


    @Override
    public BigDecimal checkout(Map<Product, Integer> itemsMap) {
//        BigDecimal appleTotalPrice = itemsMap.keySet().stream().filter(AppleDiscount.apple).map(p -> itemsMap.get(p));
        BigDecimal applePrice = appleDiscount.apply(itemsMap);
        BigDecimal orangePrice = orangeDiscount.apply(itemsMap);
        return applePrice.add(orangePrice);
    }
}
