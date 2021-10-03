package com.frangr00.cashier;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.frangr00.cashier.PricingRulesMotherTest.givenPricesForVoucherTshirtAndPants;
import static org.assertj.core.api.BDDAssertions.then;

class CheckoutAcceptanceTest {

    @Test
    void shouldCalculateWithNoDiscount() {
        Checkout checkout = new Checkout(givenPricesForVoucherTshirtAndPants("5.00", "20.00", "7.50"));

        checkout.add(Product.VOUCHER);
        checkout.add(Product.TSHIRT);
        checkout.add(Product.PANTS);

        then(checkout.amount()).isEqualByComparingTo(new BigDecimal("32.50"));
    }

    @Test
    void shouldCalculateVoucherDiscount() {
        Checkout checkout = new Checkout(givenPricesForVoucherTshirtAndPants("5.00", "20.00", "7.50"));

        checkout.add(Product.VOUCHER);
        checkout.add(Product.TSHIRT);
        checkout.add(Product.VOUCHER);

        then(checkout.amount()).isEqualByComparingTo(new BigDecimal("25.00"));
    }

    @Test
    void shouldCalculateTshirtDiscount() {
        Checkout checkout = new Checkout(givenPricesForVoucherTshirtAndPants("5.00", "20.00", "7.50"));

        checkout.add(Product.TSHIRT);
        checkout.add(Product.TSHIRT);
        checkout.add(Product.TSHIRT);
        checkout.add(Product.VOUCHER);
        checkout.add(Product.TSHIRT);

        then(checkout.amount()).isEqualByComparingTo(new BigDecimal("81.00"));
    }

    @Test
    void shouldCalculateMixinProducts() {
        Checkout checkout = new Checkout(givenPricesForVoucherTshirtAndPants("5.00", "20.00", "7.50"));

        checkout.add(Product.VOUCHER);
        checkout.add(Product.TSHIRT);
        checkout.add(Product.VOUCHER);
        checkout.add(Product.VOUCHER);
        checkout.add(Product.PANTS);
        checkout.add(Product.TSHIRT);
        checkout.add(Product.TSHIRT);

        then(checkout.amount()).isEqualByComparingTo(new BigDecimal("74.50"));
    }
}
