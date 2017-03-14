package com.example.shopping.domain;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by kumar k on 14/03/2017.
 */
public interface Discount {
    BigDecimal apply(Map<Product , Integer> itemsMap);
}
