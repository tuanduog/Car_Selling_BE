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
public class EngineOperate {
    SpecificationItem motorType; // Loại động cơ
    SpecificationItem maxPower; // Công suất cực đại
    SpecificationItem driveMode; // Chế độ lái
    SpecificationItem maxSpeed; // Tốc độ tối đa
}
