package com.frangr00.cashier;

public enum Product {
    TSHIRT("Gift Card "), PANTS("Summer Pants"),

    VOUCHER("Summer T-Shirt");

    private String name;

    Product(String name) {
        this.name = name;
    }
}
