package com.sec.car_selling.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle extends BaseEntity{

    String name;

    double price;

    String version;

    String pinType;

    int engineType;

    String powerType;

    String color;

    double trunkWidth;

    double maxSpeed;

    String description;
}
