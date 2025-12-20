package com.sec.car_selling.dto.request;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehiclePaymentRequest {

    int carId;

    String carName;

    int carVersion;

    double price;

    String color;

    UserRequest user;

    ShowroomRequest showroom;

    int paymentType;

    @Nullable
    InstallmentPaymentRequest installmentPayment;
}
