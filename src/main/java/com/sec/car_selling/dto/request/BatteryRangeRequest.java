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
public class BatteryRangeRequest {
    String batteryCapacity; // dung lượng pin
    String range; // quãn đường di chuyển
    String normalChargeTime; // thời gian sạc
    String fastChargeSupport; // sạc nhanh dc
}
