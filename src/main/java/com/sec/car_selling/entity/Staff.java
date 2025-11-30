package com.sec.car_selling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Staff extends BaseEntity{

    @Column(name = "user_id")
    Integer userId;

    String code;

    String gender;

    LocalDate birthday;

    String phone;

    String address;

    @Column(name = "identity_number")
    String identityNumber;

    @Column(name = "manager_id")
    Integer managerId;
}
