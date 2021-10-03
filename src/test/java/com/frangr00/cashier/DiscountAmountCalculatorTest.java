package com.frangr00.cashier;

import com.frangr00.cashier.discount.DiscountAmountCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.frangr00.cashier.PricingRulesMotherTest.givenPricesForVoucherTshirtAndPants;
import static org.assertj.core.api.BDDAssertions.then;

class DiscountAmountCalculatorTest {

    DiscountAmountCalculator calculator = new DiscountAmountCalculator();

    @Test
    void shouldCalculateZeroWhenNoElementIsPresent() {
        PricingRules pricingRules = givenPricesForVoucherTshirtAndPants("5.00", "20.00", "7.50");
        List<Product> emptyProducts = List.of();

        BigDecimal amountCalculated = calculator.calculate(emptyProducts, pricingRules);

        then(amountCalculated).isEqualByComparingTo("0.00");
    }

    @Test
    void shouldCalculateOneItemEach() {
        PricingRules pricingRules = givenPricesForVoucherTshirtAndPants("5.00", "20.00", "7.50");
        List<Product> products = List.of(Product.PANTS, Product.TSHIRT, Product.VOUCHER);

        BigDecimal amountCalculated = calculator.calculate(products, pricingRules);

        then(amountCalculated).isEqualByComparingTo("32.50");
    }

    @Test
    void shouldCalculate2VoucherDiscount() {
        PricingRules pricingRules = givenPricesForVoucherTshirtAndPants("5.00", "20.00", "7.50");
        List<Product> products = List.of(Product.VOUCHER, Product.VOUCHER);

        BigDecimal amountCalculated = calculator.calculate(products, pricingRules);

        then(amountCalculated).isEqualByComparingTo("5.00");
    }

    @Test
    void shouldCalculate3VoucherDiscount() {
        PricingRules pricingRules = givenPricesForVoucherTshirtAndPants("5.00", "20.00", "7.50");
        List<Product> products = List.of(Product.VOUCHER, Product.VOUCHER, Product.VOUCHER);

        BigDecimal amountCalculated = calculator.calculate(products, pricingRules);

        then(amountCalculated).isEqualByComparingTo("10.00");
    }

    @Test
    void shouldCalculate3TshirtDiscount() {
        PricingRules pricingRules = givenPricesForVoucherTshirtAndPants("5.00", "20.00", "7.50");
        List<Product> products = List.of(Product.TSHIRT, Product.TSHIRT, Product.TSHIRT);

        BigDecimal amountCalculated = calculator.calculate(products, pricingRules);

        then(amountCalculated).isEqualByComparingTo("57.00");
    }

    @Test
    void shouldCalculate2TshirtDiscount() {
        PricingRules pricingRules = givenPricesForVoucherTshirtAndPants("5.00", "20.00", "7.50");
        List<Product> products = List.of(Product.TSHIRT, Product.TSHIRT);

        BigDecimal amountCalculated = calculator.calculate(products, pricingRules);

        then(amountCalculated).isEqualByComparingTo("40.00");
    }
}