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
public class ExteriorFeature {
    SpecificationItem designDescription;  // Mô tả thiết kế
    SpecificationItem daytimeLED;         // Đèn LED định vị ban ngày
    SpecificationItem wheels;             // Mâm xe
    SpecificationItem sideMirrors;        // Gương chiếu hậu
    SpecificationItem bumper;             // Cản trước/sau
}
