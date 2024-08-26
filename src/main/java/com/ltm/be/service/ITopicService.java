package com.ltm.be.service;

import com.ltm.be.dto.GroupDto;
import com.ltm.be.entity.GroupEntity;
import com.ltm.be.payload.request.TopicRequest;
import com.ltm.be.service.base.IBaseService;

public interface ITopicService extends IBaseService<GroupDto, GroupEntity> {
    void create(TopicRequest request);
}
