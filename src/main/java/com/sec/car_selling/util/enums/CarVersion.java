package com.sec.car_selling.util.enums;

public enum CarVersion {
    ECO(1), PLUS(2);

    private final int value;
    CarVersion(int value) {
        this.value = value;
    }
    public int getValue() { return this.value; }
}
