package com.frangr00.cashier.discount;

import com.frangr00.cashier.PricingRules;
import com.frangr00.cashier.Product;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountCalculator {
    BigDecimal calculate(List<Product> products, PricingRules pricingRules);

}
