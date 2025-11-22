package com.sec.car_selling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Action extends BaseEntity {

    @Column(name = "module_id")
    int moduleId;

    String name;

    String path;
}
