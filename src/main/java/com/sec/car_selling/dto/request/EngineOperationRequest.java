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
public class EngineOperationRequest {
    String motorType; // Loại động cơ
    String maxPower; // Công suất cực đại
    String driveMode; // Chế độ lái
    String maxSpeed;
}
