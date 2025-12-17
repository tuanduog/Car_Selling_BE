package com.sec.car_selling.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SizeWeight {
    SpecificationItem dimension;
    SpecificationItem wheelBase;
    SpecificationItem  groundClearance;
    SpecificationItem curbWeight;
    SpecificationItem seat;
    SpecificationItem trunkVolume;
}
