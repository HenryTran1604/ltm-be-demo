package com.ltm.be.service;

import com.ltm.be.dto.GroupDto;
import com.ltm.be.entity.GroupEntity;
import com.ltm.be.payload.request.GroupRequest;
import com.ltm.be.service.base.IBaseService;

public interface IGroupService {
    void create(GroupRequest request);
}
