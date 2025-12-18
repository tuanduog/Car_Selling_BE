package com.sec.car_selling.dto.response;

import com.sec.car_selling.entity.BatteryRange;
import com.sec.car_selling.entity.EngineOperate;
import com.sec.car_selling.entity.InteriorFeature;
import com.sec.car_selling.entity.SizeWeight;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCustomerResponse {

    int id;

    String imageUrl;

    String name;

    double price;

    LocalDate releaseDate;

    String code;

    int version;

    String description;

    String detail;

    SizeWeight sizeWeight;

    EngineOperate engineOperate;

    BatteryRange batteryRange;

    InteriorFeature interiorFeature;

    public VehicleCustomerResponse(int id, String imageUrl, String name, double price, LocalDate releaseDate){
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
    }
}
