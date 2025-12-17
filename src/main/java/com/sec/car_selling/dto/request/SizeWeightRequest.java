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
public class SizeWeightRequest {
    String dimension;
    String wheelBase;
    String groundClearance;
    String curbWeight;
    String seat;
    String trunkVolume;
}
