package com.sec.car_selling.util.enums;

public enum PaymentType {
    INSTALLMENT(0), FULL(1);

    private final int value;
    PaymentType(int value) { this.value = value; }
    public int getValue() { return value; }
}
