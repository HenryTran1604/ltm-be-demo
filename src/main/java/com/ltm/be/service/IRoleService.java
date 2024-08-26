package com.ltm.be.service;

import com.ltm.be.entity.RoleEntity;
import com.ltm.be.payload.request.RoleRequest;
import com.ltm.be.service.base.IBaseService;

public interface IRoleService  {
    RoleEntity createRoleIfNotFound(String name);
    RoleEntity create(RoleRequest request);
}
