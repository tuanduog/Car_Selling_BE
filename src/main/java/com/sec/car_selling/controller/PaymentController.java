package com.sec.car_selling.controller;

import com.sec.car_selling.dto.request.PaymentRequest;
import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.service.PaymentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/v1")
    public ResponseEntity<?> getList(
            @PageableDefault(page = 0, size = 5) Pageable pageable,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer paymentStatus,
            @RequestParam(required = false) Integer paymentType
    ){
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), paymentService.getList(pageable, keyword, paymentStatus, paymentType), "list successful"));
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), paymentService.getById(id), "get successful"));
    }

    @PutMapping("/cancelled/v1/{id}")
    public ResponseEntity<?> cancelledById(@PathVariable int id){
        paymentService.cancelledById(id);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "cancelled successful"));
    }

    @PutMapping("/accepted/v1/{id}")
    public ResponseEntity<?> acceptedById(@PathVariable int id){
        paymentService.acceptedById(id);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "accepted successful"));
    }
}
