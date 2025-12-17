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
public class BatteryRange {
    SpecificationItem batteryCapacity; // dung lượng pin
    SpecificationItem range; // quãn đường di chuyển
    SpecificationItem normalChargeTime; // thời gian sạc
    SpecificationItem fastChargeSupport; // sạc nhanh dc
}
