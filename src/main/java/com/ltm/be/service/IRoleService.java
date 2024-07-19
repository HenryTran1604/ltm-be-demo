package com.ltm.be.service;

import com.ltm.be.entity.RoleEntity;

public interface IRoleService {
    RoleEntity createRoleIfNotFound(String name);
}
