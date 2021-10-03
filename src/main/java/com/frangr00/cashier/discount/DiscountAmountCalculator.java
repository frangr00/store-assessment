package com.frangr00.cashier.discount;

import com.frangr00.cashier.AmountCalculator;
import com.frangr00.cashier.PricingRules;
import com.frangr00.cashier.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DiscountAmountCalculator implements AmountCalculator {
    private final Map<Product, DiscountCalculator> productDiscountMap;
    private final DiscountCalculator defaultDiscountCalculator;

    public DiscountAmountCalculator() {
        // @formatter:off
        productDiscountMap = Map.of(Product.VOUCHER, new Discount2x1Calculator(),
                                    Product.TSHIRT, new SpecialPriceMoreThan3DiscountCalculator(new BigDecimal("19.00")));
        // @formatter:on

        defaultDiscountCalculator = new NoDiscountCalculator();
    }

    @Override
    public BigDecimal calculate(List<Product> products, PricingRules pricingRules) {
        Map<Product, List<Product>> productsGroupedByType = products.stream()
                                                                    .collect(Collectors.groupingBy(Function.identity()));

        return productsGroupedByType.entrySet()
                                    .stream()
                                    .map(calculateForTypeProduct(pricingRules))
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Function<Map.Entry<Product, List<Product>>, BigDecimal> calculateForTypeProduct(PricingRules pricingRules) {
        return e -> productDiscountMap.getOrDefault(e.getKey(), defaultDiscountCalculator)
                                      .calculate(e.getValue(), pricingRules);
    }

}
