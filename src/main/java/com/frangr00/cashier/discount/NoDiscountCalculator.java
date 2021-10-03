package com.frangr00.cashier.discount;

import com.frangr00.cashier.PricingRules;
import com.frangr00.cashier.Product;

import java.math.BigDecimal;
import java.util.List;

public class NoDiscountCalculator implements DiscountCalculator {
    @Override
    public BigDecimal calculate(List<Product> products, PricingRules pricingRules) {
        Product product = products.stream()
                                  .findAny()
                                  .orElseThrow();
        BigDecimal productPrice = pricingRules.get(product);
        BigDecimal numberOfItems = BigDecimal.valueOf(products.size());
        return productPrice.multiply(numberOfItems);
    }
}
