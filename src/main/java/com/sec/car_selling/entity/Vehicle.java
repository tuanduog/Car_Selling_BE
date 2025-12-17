package com.sec.car_selling.entity;

import com.sec.car_selling.util.JsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle extends BaseEntity{

    String imageUrl;

    String name;

    double price;

    int version;

    String color;

    LocalDate releaseDate;

    String detail;

    String description;

    @Column(columnDefinition = "json")
    @Convert(converter = JsonConverter.class)
    SizeWeight sizeWeight;

    @Column(columnDefinition = "json")
    @Convert(converter = JsonConverter.class)
    EngineOperate engineOperate;

    @Column(columnDefinition = "json")
    @Convert(converter = JsonConverter.class)
    BatteryRange batteryRange;

    @Column(columnDefinition = "json")
    @Convert(converter = JsonConverter.class)
    ExteriorFeature exteriorFeature;

    @Column(columnDefinition = "json")
    @Convert(converter = JsonConverter.class)
    InteriorFeature interiorFeature;
}
