package com.sec.car_selling.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
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

    String managerCode;

    String managerFullName;

    String role;

    int status;

    public StaffResponse(
            int id,
            String code,
            String fullName,
            String email,
            String phone,
            LocalDate birthday,
            String gender,
            String address,
            String role,
            int status
    ) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.status = status;
        this.role = role;
    }
}
