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
@AllArgsConstructor
public class PaymentResponse {

    int id;

    String code;

    String customerName;

    String carName;

    BigDecimal price;

    int paymentType;

    LocalDateTime paymentDate;

    int paymentStatus;
}

