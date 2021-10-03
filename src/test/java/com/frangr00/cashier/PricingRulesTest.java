package com.frangr00.cashier;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

class PricingRulesTest {

    @Test
    void shouldThrowExceptionIfAnyProductIsNotSet() {
        //No Given

        Throwable throwable = catchThrowable(() -> PricingRules.create()
                                                               .add(Product.PANTS, new BigDecimal("1.00"))
                                                               .add(Product.VOUCHER, new BigDecimal("3.00"))
                                                               .build());

        then(throwable).isExactlyInstanceOf(IllegalStateException.class)
                       .hasMessage("You must fill all prices for all products");

    }

    @Test
    void shouldThrowExceptionIfProductParamIsNull() {
        //No Given

        Throwable throwable = catchThrowable(() -> PricingRules.create()
                                                               .add(null, new BigDecimal("1.00"))
                                                               .build());

        then(throwable).isExactlyInstanceOf(IllegalArgumentException.class)
                       .hasMessage("Product can not be null");

    }

    @Test
    void shouldThrowExceptionIfPriceParamIsNull() {
        //No Given

        Throwable throwable = catchThrowable(() -> PricingRules.create()
                                                               .add(Product.PANTS, null)
                                                               .build());

        then(throwable).isExactlyInstanceOf(IllegalArgumentException.class)
                       .hasMessage("Price can not be null");

    }

    @Test
    void shouldCreate3PricesOk() {
        //No Given

        PricingRules pricingRules = PricingRules.create()
                                                .add(Product.PANTS, new BigDecimal("1.00"))
                                                .add(Product.TSHIRT, new BigDecimal("2.00"))
                                                .add(Product.VOUCHER, new BigDecimal("3.00"))
                                                .build();

        then(pricingRules.get(Product.PANTS)).isEqualByComparingTo(new BigDecimal("1.00"));
        then(pricingRules.get(Product.TSHIRT)).isEqualByComparingTo(new BigDecimal("2.00"));
        then(pricingRules.get(Product.VOUCHER)).isEqualByComparingTo(new BigDecimal("3.00"));
    }
}