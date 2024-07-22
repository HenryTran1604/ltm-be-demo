package com.ltm.be.service.impl;

import com.ltm.be.entity.RoleEntity;
import com.ltm.be.repository.RoleRepository;
import com.ltm.be.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository roleRepository;
    @Override
    public RoleEntity createRoleIfNotFound(String name) {
        if (!roleRepository.existsByName(name)) {
            RoleEntity role = RoleEntity.builder().name(name).build();
            return roleRepository.save(role);
        }
        return null;
    }
}
