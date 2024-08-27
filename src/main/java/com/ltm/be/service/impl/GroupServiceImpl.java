package com.ltm.be.service.impl;

import com.ltm.be.entity.GroupEntity;
import com.ltm.be.payload.request.GroupRequest;
import com.ltm.be.repository.GroupRepository;
import com.ltm.be.service.IGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements IGroupService {
    private final GroupRepository groupRepository;
    private final GroupConv
    @Override
    public void create(GroupRequest request) {
        GroupEntity entity = GroupEntity.builder()
                .name(request.getName())
                .build();
        create(entity);
    }

}
