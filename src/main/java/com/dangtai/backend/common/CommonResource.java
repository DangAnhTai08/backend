package com.dangtai.backend.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CommonResource {

    @Value("${service.jwt-secret}")
    private String jwtSecret;

    @Value("${service.jwt-expiration}")
    private long jwtExpiration;
}
