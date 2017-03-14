package com.example.shopping.domain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Created by kumar k on 14/03/2017.
 */
@Service
public class OrangeDiscount implements Discount
{
    @Override
    public BigDecimal apply(Map<Product, Integer> itemMap) {
        UnaryOperator<Integer> qty = q -> q > 2 ? q - (q / 3) : q;
        Function<Product, BigDecimal> orangePrice = p -> p.getPrice().setScale(2, RoundingMode.HALF_UP).multiply(
                BigDecimal.valueOf(qty.apply(itemMap.getOrDefault(p, 0)))
        );
        return itemMap.keySet().stream().filter(orange).map(orangePrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Predicate<Product> orange = p -> "ORANGE".equalsIgnoreCase(p.getName());
}
