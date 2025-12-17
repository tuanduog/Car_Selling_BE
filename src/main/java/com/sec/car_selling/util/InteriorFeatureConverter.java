package com.sec.car_selling.util;

import com.sec.car_selling.entity.InteriorFeature;
import jakarta.persistence.Converter;

@Converter
public class InteriorFeatureConverter extends JsonConverter<InteriorFeature> {
    public InteriorFeatureConverter() {
        super(InteriorFeature.class);
    }
}
