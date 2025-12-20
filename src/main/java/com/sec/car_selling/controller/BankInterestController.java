package com.sec.car_selling.controller;

import com.sec.car_selling.dto.request.CalculateInstallmentRequest;
import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.service.BankInterestService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank-interest")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class BankInterestController {

    BankInterestService bankInterestService;

    @GetMapping("/v1")
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), bankInterestService.getList(), "get successful"));
    }

    @PostMapping("/calculate/v1")
    public ResponseEntity<?> calculateInstallment(@RequestBody CalculateInstallmentRequest request){
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), bankInterestService.calculateInstallment(request), "calculate successful"));
    }
}
