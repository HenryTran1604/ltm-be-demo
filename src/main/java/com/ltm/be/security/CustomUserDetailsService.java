package com.ltm.be.security;

import com.ltm.be.entity.UserEntity;
import com.ltm.be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByStudentCode(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(userEntity);
    }
}
