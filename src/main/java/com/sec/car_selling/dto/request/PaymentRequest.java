package com.sec.car_selling.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {

    int carId;

    String carName;

    String carColor;

    int carVersion;

    BigDecimal price;

    String paymentName;

    String paymentEmail;

    String phone;

    String identityNumber;

    String showRoomCity;

    String showRoomName;

    int paymentType;

    InstallmentPaymentRequest installment;
}
