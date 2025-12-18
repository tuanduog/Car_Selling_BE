package com.sec.car_selling.dto.response;

import com.sec.car_selling.entity.BatteryRange;
import com.sec.car_selling.entity.EngineOperate;
import com.sec.car_selling.entity.InteriorFeature;
import com.sec.car_selling.entity.SizeWeight;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {

    int id;

    String code;

    String name;

    int version;

    double price;

    LocalDate releaseDate;

    String description;

    String detail;

    String imageUrl;

    SizeWeight sizeWeight;

    EngineOperate engineOperate;

    BatteryRange batteryRange;

    InteriorFeature interiorFeature;

    public VehicleResponse(int id, String code, String name, int version, double price, LocalDate releaseDate){
        this.id = id;
        this.code = code;
        this.name = name;
        this.version = version;
        this.price = price;
        this.releaseDate = releaseDate;
    }
}
