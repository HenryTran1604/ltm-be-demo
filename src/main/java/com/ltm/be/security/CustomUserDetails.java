package com.ltm.be.security;

import com.ltm.be.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private UUID id;
    private String username;
    private String ipAddress;
    private String password;
    private OffsetDateTime createdAt;
    private Collection<? extends GrantedAuthority> roles;
    public CustomUserDetails(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUserName();
        this.ipAddress = user.getIpAddress();
        this.password = user.getPassword();
        this.createdAt = user.getCreatedAt();
        this.roles = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
