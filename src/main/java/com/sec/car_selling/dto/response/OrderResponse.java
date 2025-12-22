package com.sec.car_selling.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    int id;

    String code;

    String carName;

    String carColor;

    int carVersion;

    BigDecimal price;

    LocalDateTime orderDate;

    int paymentType;

    int paymentStatus;

    BigDecimal downPayment;

    int loanDuration;

    String bankName;

    BigDecimal interestRate;

    String showRoomCity;

    String showRoomName;

    public OrderResponse(int id, String code, String carName, String carColor, int carVersion, BigDecimal price, LocalDateTime orderDate, int paymentType, int paymentStatus, BigDecimal downPayment) {
        this.id = id;
        this.code = code;
        this.carName = carName;
        this.carColor = carColor;
        this.carVersion = carVersion;
        this.price = price;
        this.orderDate = orderDate;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
        this.downPayment = downPayment;
    }
}
