package com.sec.car_selling.util.enums;

public enum Status {
    ACTIVE(1), INACTIVE(0);

    private final int value;

    Status(int value){ this.value = value; }

    public int getValue() {return value;}
}
