package com.sec.car_selling.dto.response;

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
public class StaffResponse {

    int id;

    String code;

    String fullName;

    String email;

    String phone;

    LocalDate birthday;

    String gender;

    String address;

    Integer managerId;

    String role;

    int status;
}
