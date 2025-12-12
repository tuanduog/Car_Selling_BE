package com.sec.car_selling.controller;

import com.sec.car_selling.dto.request.AddEmployeeRequest;
import com.sec.car_selling.dto.request.UpdateEmployeeRequest;
import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.service.StaffService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffController {

    StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/v1")
    public ResponseEntity<?> getList(
            @PageableDefault(page = 0, size = 5) Pageable pageable,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String role
            ){
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), staffService.getList(pageable, keyword, status, role), "Successful"));
    }

    @PostMapping("/v1")
    public ResponseEntity<?> addStaff(@RequestBody AddEmployeeRequest request){
        staffService.addStaff(request);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "Added Successfully"));
    }

    @PutMapping("/v1/{id}")
    public ResponseEntity<?> updateStaff(@RequestBody UpdateEmployeeRequest request, @PathVariable int id){
        staffService.updateStaff(request, id);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "Updated Successfully"));
    }

    @PutMapping("/inactive/v1/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable int id){
        System.out.println(id);
        staffService.deleteStaff(id);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "Deleted Successfully"));
    }

}
