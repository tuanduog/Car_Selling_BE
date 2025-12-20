package com.sec.car_selling.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InstallmentDetail {

    int month;

    BigDecimal openingBalance;     // Dư nợ đầu kỳ
    BigDecimal principalPayment;   // Trả nợ gốc
    BigDecimal interestPayment;    // Trả lãi
    BigDecimal totalPayment;       // Tổng thanh toán
    BigDecimal closingBalance;     // Dư nợ cuối kỳ
}
