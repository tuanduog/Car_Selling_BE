package com.sec.car_selling.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public abstract class JsonConverter<T> implements AttributeConverter<T, String> {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final Class<T> clazz;

    protected JsonConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert to JSON", e);
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON", e);
        }
    }
}
