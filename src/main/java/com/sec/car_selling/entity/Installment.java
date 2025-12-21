package com.sec.car_selling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Installment extends BaseEntity {

    @Column(name = "payment_id")
    int paymentId;

    @Column(name = "loan_duration")
    int loanDuration;

    @Column(name = "down_payment")
    BigDecimal downPayment;

    @Column(name = "bank_id")
    int bankId;
}
