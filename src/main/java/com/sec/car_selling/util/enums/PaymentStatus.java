package com.sec.car_selling.util.enums;

public enum PaymentStatus {
    PENDING(0), IN_PROGRESS(1), PAID(2), CANCELLED(3);

    private int value;
    PaymentStatus(int value) { this.value = value; }
    public int getValue() { return value; }
}
