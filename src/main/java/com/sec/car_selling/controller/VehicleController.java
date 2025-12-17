package com.sec.car_selling.controller;

import com.sec.car_selling.dto.request.VehicleRequest;
import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.service.VehicleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/vehicle")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleController {

    VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(value = "/v1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addVehicle(@RequestPart("vehicle") VehicleRequest request,
                                        @RequestPart(value = "image", required = false) MultipartFile image){
        System.out.println(request);
        vehicleService.addVehicle(request, image);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "vehicle added successfully"));
    }

    @GetMapping(value = "/v1")
    public ResponseEntity<?> getList(Pageable pageable, String keyword){
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), vehicleService.getList(pageable, keyword), "vehicles listed successfully"));
    }

    @PutMapping(value = "/delete/v1/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Integer id){
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "vehicle deleted successfully"));
    }
}
