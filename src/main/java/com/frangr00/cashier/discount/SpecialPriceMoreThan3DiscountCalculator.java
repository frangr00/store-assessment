package com.frangr00.cashier.discount;

import com.frangr00.cashier.PricingRules;
import com.frangr00.cashier.Product;

import java.math.BigDecimal;
import java.util.List;

public class SpecialPriceMoreThan3DiscountCalculator implements DiscountCalculator {

    private final BigDecimal discountedPrice;

    public SpecialPriceMoreThan3DiscountCalculator(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Override
    public BigDecimal calculate(List<Product> products, PricingRules pricingRules) {
        Product product = products.stream()
                                  .findFirst()
                                  .orElseThrow();
        BigDecimal pricePerUnit = products.size() >= 3 ? discountedPrice : pricingRules.get(product);

        BigDecimal items = BigDecimal.valueOf(products.size());
        return pricePerUnit.multiply(items);
    }
}
