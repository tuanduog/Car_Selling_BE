package com.sec.car_selling.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreatePaymentLinkRequest {
    private String carName;
    private String returnUrl;
    private int price;
    private String cancelUrl;
}