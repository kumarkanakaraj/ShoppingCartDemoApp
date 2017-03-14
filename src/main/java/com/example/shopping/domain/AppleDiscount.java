package com.example.shopping.domain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by kumar k on 14/03/2017.
 */
@Service
public class AppleDiscount implements Discount{
    @Override
    public BigDecimal apply(Map<Product, Integer> itemMap) {
        Function<Product, BigDecimal> applePrice = p -> itemMap.getOrDefault(p, 0) > 1 ? p.getPrice().setScale(2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(itemMap.get(p) / 2))
                : p.getPrice().setScale(2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(itemMap.get(p))
        );
        return itemMap.keySet().stream().filter(apple).map(applePrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Predicate<Product> apple = p -> "APPLE".equalsIgnoreCase(p.getName());
}
