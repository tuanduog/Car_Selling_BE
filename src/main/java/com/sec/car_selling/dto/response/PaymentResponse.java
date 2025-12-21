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
@NoArgsConstructor
public class PaymentResponse {

    int id;

    String code;

    String customerName;

    String carName;

    BigDecimal price;

    int paymentType;

    LocalDateTime paymentDate;

    int paymentStatus;

    int carVersion;

    String carColor;

    String customerEmail;

    String phone;

    String identityNumber;

    String showRoomCity;

    String showRoomName;

    int loanDuration;

    BigDecimal downPayment;

    String bankName;

    BigDecimal interestRate;

    public PaymentResponse(int id, String code, String customerName, String carName, BigDecimal price, int paymentType, LocalDateTime paymentDate, int paymentStatus) {
        this.id = id;
        this.code = code;
        this.customerName = customerName;
        this.carName = carName;
        this.price = price;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public PaymentResponse(int id, String code, String customerName, String carName, BigDecimal price, int paymentType, LocalDateTime paymentDate, int paymentStatus,
                           int carVersion, String carColor, String customerEmail, String phone, String identityNumber, String showRoomCity, String showRoomName) {
        this.id = id;
        this.code = code;
        this.customerName = customerName;
        this.carName = carName;
        this.price = price;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.carVersion = carVersion;
        this.carColor = carColor;
        this.customerEmail = customerEmail;
        this.phone = phone;
        this.identityNumber = identityNumber;
        this.showRoomCity = showRoomCity;
        this.showRoomName = showRoomName;
    }

    public PaymentResponse(int id, String code, String customerName, String carName, BigDecimal price, int paymentType, LocalDateTime paymentDate, int paymentStatus,
                           int carVersion, String carColor, String customerEmail, String phone, String identityNumber, String showRoomCity, String showRoomName,
                           int loanDuration, BigDecimal downPayment, String bankName, BigDecimal interestRate) {
        this.id = id;
        this.code = code;
        this.customerName = customerName;
        this.carName = carName;
        this.price = price;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.carVersion = carVersion;
        this.carColor = carColor;
        this.customerEmail = customerEmail;
        this.phone = phone;
        this.identityNumber = identityNumber;
        this.showRoomCity = showRoomCity;
        this.showRoomName = showRoomName;
        this.loanDuration = loanDuration;
        this.downPayment = downPayment;
        this.bankName = bankName;
        this.interestRate = interestRate;
    }
}

