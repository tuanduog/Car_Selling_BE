package com.sec.car_selling.util;

import com.sec.car_selling.entity.BatteryRange;
import jakarta.persistence.Converter;

@Converter
public class BatteryRangeConverter extends JsonConverter<BatteryRange> {
    public BatteryRangeConverter() {
        super(BatteryRange.class);
    }
}
