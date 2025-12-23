package com.sec.car_selling.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.payos.PayOS;

@Configuration
public class PayOSConfig {

    @Value("${payos.client-id}")
    private String clientId;

    @Value("${payos.api-key}")
    private String apiKey;

    @Value("${payos.checksum-key}")
    private String checksumKey;
    @Bean
    public PayOS payOS() {
        return new PayOS(
                clientId,
                apiKey,
                checksumKey
        );
    }
    @PostConstruct
    public void logPayOSConfig() {
        System.out.println("PayOS clientId = " + clientId);
        System.out.println("PayOS apiKey = " + apiKey);
        System.out.println("PayOS checksumKey = " + checksumKey);
    }
}