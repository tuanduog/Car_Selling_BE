package com.sec.car_selling.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CalculateInstallmentResponse {

    BigDecimal loanAmount;

    BigDecimal totalInterest;

    BigDecimal totalPayment;

    BigDecimal estimateMonthlyPayment;

    List<InstallmentDetail> installmentDetails;
}
