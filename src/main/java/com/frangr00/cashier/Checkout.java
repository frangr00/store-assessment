package com.frangr00.cashier;

import com.frangr00.cashier.discount.DiscountAmountCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Checkout {
    private final PricingRules pricingRules;
    private final List<Product> products;
    private final AmountCalculator amountCalculator;

    /**
     * @param pricingRules You could create it through the PricingRules builder:
     *       <pre>{@code
     *       PricingRules.create()
     *                    .add(Product.VOUCHER, new BigDecimal("5.00"))
     *                    .add(Product.TSHIRT, new BigDecimal("20.00"))
     *                    .add(Product.PANTS, new BigDecimal("7.50"))
     *                    .build()}
     *       </pre>
     */
    public Checkout(PricingRules pricingRules) {
        amountCalculator = new DiscountAmountCalculator();
        this.pricingRules = pricingRules;
        products = new ArrayList<>();
    }

    public void add(Product product) {
        products.add(product);
    }

    public BigDecimal amount() {
        return amountCalculator.calculate(products, pricingRules);
    }
}
