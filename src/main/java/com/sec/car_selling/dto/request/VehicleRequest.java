package com.sec.car_selling.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleRequest {

    String code;

    String name;

    double price;

    int version;

    LocalDate releaseDate;

    String description;

    String detail;

    SizeWeightRequest sizeWeight;

    EngineOperationRequest engineOperation;

    BatteryRangeRequest batteryRange;

    InteriorFeatureRequest interiorFeature;
}
