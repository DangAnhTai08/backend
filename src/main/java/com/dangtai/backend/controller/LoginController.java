package com.dangtai.backend.controller;


import com.dangtai.backend.auth.JwtTokenProvider;
import com.dangtai.backend.dto.LoginRequest;
import com.dangtai.backend.entity.SaleUserEntity;
import com.dangtai.backend.entity.SaleUserRoleEntity;
import com.dangtai.backend.repository.SaleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class LoginController {

    private final SaleUserRepository saleUserRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    //Login admin
    @PostMapping("login-admin")
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );

        // Set chuỗi authentication đó cho UserPrincipal
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SaleUserEntity saleUserEntity = saleUserRepository.findByUserName(loginRequest.getUsername());
        Set<SaleUserRoleEntity> roles = saleUserEntity.getUserRoleSet();

        boolean isAdmin = false;
        for (SaleUserRoleEntity role : roles) {
            if (role.getSaleRoleEntity().getRoleName().equals("ADMIN")) {
                isAdmin = true;
            }
        }
        if (authentication != null && isAdmin) {
            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok().body(jwt);
        }
        return ResponseEntity.ok().body("Failed");
    }
}
