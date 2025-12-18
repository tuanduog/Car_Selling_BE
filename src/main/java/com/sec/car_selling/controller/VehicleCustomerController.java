package com.sec.car_selling.controller;

import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.service.VehicleCustomerService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/vehicle")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleCustomerController {

    VehicleCustomerService vehicleCustomerService;

    public VehicleCustomerController(VehicleCustomerService vehicleCustomerService) {
        this.vehicleCustomerService = vehicleCustomerService;
    }

    @GetMapping("/v1")
    public ResponseEntity<?> getAllVehicle(){
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), vehicleCustomerService.getAllVehicles(), "get new vehicles successful"));
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), vehicleCustomerService.getById(id), "get vehicle successful"));
    }
}
