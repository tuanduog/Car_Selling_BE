package com.sec.car_selling.util.enums;

public enum Status {
    INACTIVE(0), ACTIVE(1), DELETED(2);

    private final int value;

    Status(int value){ this.value = value; }

    public int getValue() {return value;}
}
