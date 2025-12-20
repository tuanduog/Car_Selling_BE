package com.sec.car_selling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "bank_interest")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankInterest extends BaseEntity {

    String bank;

    @Column(name = "interest_rate")
    BigDecimal interestRate;
}
