package com.dangtai.backend.dto;

import com.dangtai.backend.entity.SaleRoleEntity;
import com.dangtai.backend.entity.SaleUserEntity;
import com.dangtai.backend.entity.SaleUserRoleEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserPrincipal implements UserDetails {

    private long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(SaleUserEntity saleUserEntity) {
        Set<SaleUserRoleEntity> roles = saleUserEntity.getUserRoleSet();
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getSaleRoleEntity().getRoleName()))
                .collect(Collectors.toList());

        return new UserPrincipal(
                saleUserEntity.getUserId(),
                saleUserEntity.getUserName(),
                saleUserEntity.getUserPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
