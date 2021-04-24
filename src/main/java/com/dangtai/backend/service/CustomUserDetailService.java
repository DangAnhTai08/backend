package com.dangtai.backend.service;

import com.dangtai.backend.dto.UserPrincipal;
import com.dangtai.backend.entity.SaleUserEntity;
import com.dangtai.backend.repository.SaleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final SaleUserRepository saleUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {

        SaleUserEntity saleUserEntity = saleUserRepository.findByUserName(username);
        if (Objects.isNull(saleUserEntity)) {
            throw new UsernameNotFoundException("Not found account with username: " + username);
        }

        return UserPrincipal.create(saleUserEntity);
    }


    @Transactional
    public UserDetails loadUserById(long id) {

        SaleUserEntity saleUserEntity = saleUserRepository.findByUserId(id);
        if (Objects.isNull(saleUserEntity)) {
            throw new UsernameNotFoundException("User not found with id " + id);
        }

        return UserPrincipal.create(saleUserEntity);
    }
}
