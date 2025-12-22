package com.sec.car_selling.controller;

import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.service.OrderService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class OrderController {

    OrderService orderService;

    @GetMapping("/v1")
    public ResponseEntity<?> getList(){
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), orderService.getList(), "get list successfully"));
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), orderService.getById(id), "get id successfully"));
    }
}
