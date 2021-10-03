package com.frangr00.cashier;

import java.math.BigDecimal;

public class PricingRulesMotherTest {

    public static PricingRules givenPricesForVoucherTshirtAndPants(String voucher, String tshirt, String pants) {
        return PricingRules.create()
                           .add(Product.VOUCHER, new BigDecimal(voucher))
                           .add(Product.TSHIRT, new BigDecimal(tshirt))
                           .add(Product.PANTS, new BigDecimal(pants))
                           .build();
    }

}
