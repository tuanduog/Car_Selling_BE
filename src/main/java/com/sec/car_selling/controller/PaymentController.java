package com.sec.car_selling.controller;

import com.sec.car_selling.dto.request.PaymentRequest;
import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.service.PaymentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class PaymentController {

    PaymentService paymentService;

    @PostMapping("/v1")
    public ResponseEntity<?> addPayment(@RequestBody PaymentRequest request){
        paymentService.addPayment(request);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "add successful"));
    }
}
