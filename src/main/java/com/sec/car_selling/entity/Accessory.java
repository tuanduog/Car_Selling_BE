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
@Table(name = "accessory")
public class Accessory extends BaseEntity{

    String name;

    double price;

    String description;

    String detail;
}
