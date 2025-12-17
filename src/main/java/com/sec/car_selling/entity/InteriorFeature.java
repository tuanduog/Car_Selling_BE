package com.sec.car_selling.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InteriorFeature {
    SpecificationItem centralScreen; // màn hình trung tâm
    SpecificationItem operatingSystem; // hệ điều hành
    SpecificationItem airConditioning; // điều hòa
    SpecificationItem seats; // ghế ngồi
    SpecificationItem connectivity; // kết nối
}
