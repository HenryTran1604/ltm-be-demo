package com.ltm.be.service.impl;

import com.ltm.be.converter.TopicConverter;
import com.ltm.be.dto.GroupDto;
import com.ltm.be.entity.GroupEntity;
import com.ltm.be.payload.request.TopicRequest;
import com.ltm.be.repository.GroupRepository;
import com.ltm.be.service.ITopicService;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl extends BaseServiceImpl<GroupDto, GroupEntity> implements ITopicService {
    public TopicServiceImpl(GroupRepository groupRepository,
                            TopicConverter topicConverter) {
        super(groupRepository, topicConverter);
    }

    @Override
    public void create(TopicRequest request) {
        GroupEntity entity = GroupEntity.builder()
                .name(request.getName())
                .build();
        create(entity);
    }

}
