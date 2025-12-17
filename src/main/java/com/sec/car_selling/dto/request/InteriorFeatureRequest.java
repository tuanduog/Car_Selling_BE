package com.sec.car_selling.dto.request;

import com.sec.car_selling.entity.SpecificationItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InteriorFeatureRequest {
    String centralScreen; // màn hình trung tâm
    String operatingSystem; // hệ điều hành
    String airConditioning; // điều hòa
    String seats; // ghế ngồi
    String connectivity; // kết nối
}
