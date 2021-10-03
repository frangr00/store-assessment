package com.frangr00.cashier.discount;

import com.frangr00.cashier.PricingRules;
import com.frangr00.cashier.Product;

import java.math.BigDecimal;
import java.util.List;

public class Discount2x1Calculator implements DiscountCalculator {
    @Override
    public BigDecimal calculate(List<Product> products, PricingRules pricingRules) {
        BigDecimal basePrice = pricingRules.get(products.stream()
                                                        .findFirst()
                                                        .orElseThrow());
        long pairs = products.size() / 2;
        long remainder = products.size() % 2;
        return basePrice.multiply(BigDecimal.valueOf(pairs))
                        .add(basePrice.multiply(BigDecimal.valueOf(remainder)));
    }

}
