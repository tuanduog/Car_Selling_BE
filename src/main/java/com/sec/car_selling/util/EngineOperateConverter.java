package com.sec.car_selling.util;

import com.sec.car_selling.entity.EngineOperate;
import jakarta.persistence.Converter;

@Converter
public class EngineOperateConverter extends JsonConverter<EngineOperate> {
    public EngineOperateConverter() {
        super(EngineOperate.class);
    }
}

