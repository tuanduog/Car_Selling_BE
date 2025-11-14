package com.sec.car_selling.controller;

import com.sec.car_selling.dto.request.RegisterRequest;
import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.service.UserService;
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
@RequestMapping("/api/register")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class RegisterController {

    UserService userService;

    @PostMapping("/v1")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String result = userService.register(request);
        if(result.equalsIgnoreCase("Đăng ký thành công")){
            return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null,  "Đăng ký thành công"));
        }
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), null,  result));
    }

}
