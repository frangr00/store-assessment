package com.frangr00.cashier;

import java.math.BigDecimal;
import java.util.List;

public interface AmountCalculator {
    BigDecimal calculate(List<Product> products, PricingRules pricingRules);
}
