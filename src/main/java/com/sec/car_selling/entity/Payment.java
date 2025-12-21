package com.sec.car_selling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment extends BaseEntity {

    String code;

    @Column(name = "customer_id")
    Integer customerId;

    @Column(name = "showroom_id")
    Integer showRoomId;

    @Column(name = "car_id")
    Integer carId;

    @Column(name = "car_name")
    String carName;

    @Column(name = "car_color")
    String carColor;

    @Column(name = "car_version")
    int carVersion;

    int type;

    BigDecimal price;

    @Column(name = "payment_status")
    int paymentStatus;
}
