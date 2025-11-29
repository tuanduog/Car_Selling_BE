package com.sec.car_selling.util.enums;

public enum Role {
    MANAGER("Manager"), TEAM_LEADER("TeamLeader"), STAFF("Staff"), CUSTOMER("Customer");

    private final String value;

    Role(String value) {this.value = value;}

    public String getValue() {
        return value;
    }
}
