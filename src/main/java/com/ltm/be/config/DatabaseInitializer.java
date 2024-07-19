package com.ltm.be.config;

import com.ltm.be.entity.RoleEntity;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.repository.RoleRepository;
import com.ltm.be.repository.UserRepository;;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        if(!roleRepository.existsByName("ROLE_ADMIN")) {
            RoleEntity adminRole = RoleEntity.builder().name("ROLE_ADMIN").build();
            roleRepository.save(adminRole);

            if(!userRepository.existsByUsername("b20dccn327admin")) {
                UserEntity admin = UserEntity.builder()
                    .username("b20dccn327")
                    .role(adminRole)
                    .password(passwordEncoder.encode("b20dccn327@admin"))
                    .build();
                userRepository.save(admin);
            }
        }
        if(!roleRepository.existsByName("ROLE_USER")) {
            RoleEntity userRole = RoleEntity.builder().name("ROLE_USER").build();
            roleRepository.save(userRole);
        }
    }
}
