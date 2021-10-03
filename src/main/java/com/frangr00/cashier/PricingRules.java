package com.frangr00.cashier;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class PricingRules {

    private Map<Product, BigDecimal> productsPrice;

    public static Builder create() {
        return new Builder();
    }

    public BigDecimal get(Product product) {
        return productsPrice.get(product);
    }

    public static final class Builder {
        private Map<Product, BigDecimal> products;

        private Builder() {
            products = new EnumMap<>(Product.class);
        }

        public Builder add(Product product, BigDecimal price) {
            if (product == null) {
                throw new IllegalArgumentException("Product can not be null");
            }
            if (price == null) {
                throw new IllegalArgumentException("Price can not be null");
            }
            products.put(product, price);
            return this;
        }

        public PricingRules build() {
            if (products.keySet()
                        .size() != Product.values().length) {
                throw new IllegalStateException("You must fill all prices for all products");
            }

            PricingRules pricingRules = new PricingRules();
            pricingRules.productsPrice = this.products;
            return pricingRules;
        }
    }
}
