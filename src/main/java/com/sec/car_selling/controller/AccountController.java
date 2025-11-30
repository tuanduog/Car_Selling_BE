package com.sec.car_selling.controller;

import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.service.AccountService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account-manager")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/v1")
    public ResponseEntity<?> getList(
            @PageableDefault(page = 0, size = 5) Pageable pageable,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String role) {
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), accountService.getList(pageable, keyword, status, role), "Successful"));
    }

    @PutMapping("/v1/{id}")
    public ResponseEntity<?> update(@PathVariable int id){
        accountService.update(id);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "Update successful"));
    }
}
