package com.ltm.be.security;

import com.ltm.be.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String username;
    private String ip;
    private String password;
    private LocalDateTime createdAt;
    private Collection<? extends GrantedAuthority> roles;
    public CustomUserDetails(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.ip = user.getIp();
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
