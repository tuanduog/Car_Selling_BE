package com.sec.car_selling.controller;

import com.sec.car_selling.dto.request.PasswordRequest;
import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.service.ProfileService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ProfileController {

    ProfileService profileService;

    @PutMapping("/password/v1")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordRequest request){
        System.out.println(request);
        profileService.updatePassword(request);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "Cập nhật mật khẩu thành công"));
    }
}
