package com.sec.car_selling.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEmployeeRequest {

    String code;

    String fullName;

    String email;

    LocalDate birthDay;

    String gender;

    String address;

    String phone;

    Integer managerId;
}
