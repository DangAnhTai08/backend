package com.dangtai.backend.model;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String jwtToken;
}
