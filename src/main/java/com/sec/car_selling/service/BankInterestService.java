package com.sec.car_selling.service;

import com.sec.car_selling.dto.request.CalculateInstallmentRequest;
import com.sec.car_selling.dto.response.BankInterestResponse;
import com.sec.car_selling.dto.response.CalculateInstallmentResponse;
import com.sec.car_selling.dto.response.InstallmentDetail;
import com.sec.car_selling.repository.BankInterestRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
@AllArgsConstructor
public class BankInterestService {

    BankInterestRepository bankInterestRepository;

    public List<BankInterestResponse> getList() {
        return bankInterestRepository.getList();
    }

    public CalculateInstallmentResponse calculateInstallment(CalculateInstallmentRequest request){
        BigDecimal downPayment = request.getDownPayment();
        BigDecimal price = request.getPrice();
        BigDecimal bankInterest = request.getBankInterest();

        int loanYear = request.getLoanYear();
        int totalMonths = loanYear * 12;

        // so tien vay
        BigDecimal loanAmount = price.multiply(BigDecimal.ONE.subtract(downPayment));

        BigDecimal monthlyInterestRate = bankInterest.divide(
                BigDecimal.valueOf(12),
                10,
                RoundingMode.HALF_UP
        );

        // gốc trả mỗi tháng
        BigDecimal monthlyPrincipal = loanAmount.divide(
                BigDecimal.valueOf(totalMonths),
                10,
                RoundingMode.HALF_UP
        );

        // tính cho từng tháng
        BigDecimal remainingBalance = loanAmount;
        BigDecimal totalInterest = BigDecimal.ZERO;

        List<InstallmentDetail> details = new ArrayList<>();

        for (int month = 1; month <= totalMonths; month++) {

            BigDecimal openingBalance = remainingBalance;

            // Lãi tháng = dư nợ đầu kỳ * lãi suất tháng
            BigDecimal interestPayment = openingBalance
                    .multiply(monthlyInterestRate)
                    .setScale(10, RoundingMode.HALF_UP);

            BigDecimal principalPayment = monthlyPrincipal;

            BigDecimal totalMonthlyPayment =
                    principalPayment.add(interestPayment);

            BigDecimal closingBalance =
                    openingBalance.subtract(principalPayment);

            totalInterest = totalInterest.add(interestPayment);

            details.add(new InstallmentDetail(
                    month,
                    openingBalance.setScale(0, RoundingMode.HALF_UP),
                    principalPayment.setScale(0, RoundingMode.HALF_UP),
                    interestPayment.setScale(0, RoundingMode.HALF_UP),
                    totalMonthlyPayment.setScale(0, RoundingMode.HALF_UP),
                    closingBalance.max(BigDecimal.ZERO).setScale(0, RoundingMode.HALF_UP)
            ));

            remainingBalance = closingBalance;
        }

        // tổng tiền phải trả
        BigDecimal totalPayment = loanAmount.add(totalInterest);

        // ước tính trả góp hàng tháng
        BigDecimal estimateMonthlyPayment =
                details.get(0).getTotalPayment();

        loanAmount = loanAmount.setScale(0, RoundingMode.HALF_UP);
        totalInterest = totalInterest.setScale(0, RoundingMode.HALF_UP);
        totalPayment = totalPayment.setScale(0, RoundingMode.HALF_UP);
        estimateMonthlyPayment = estimateMonthlyPayment.setScale(0, RoundingMode.HALF_UP);
        return new CalculateInstallmentResponse(
                loanAmount,
                totalInterest,
                totalPayment,
                estimateMonthlyPayment,
                details
        );
    }
}
