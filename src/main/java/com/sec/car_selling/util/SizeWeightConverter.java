package com.sec.car_selling.util;

import com.sec.car_selling.entity.SizeWeight;
import jakarta.persistence.Converter;

@Converter
public class SizeWeightConverter extends JsonConverter<SizeWeight> {
    public SizeWeightConverter() {
        super(SizeWeight.class);
    }
}