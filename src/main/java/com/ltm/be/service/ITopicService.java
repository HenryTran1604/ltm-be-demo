package com.ltm.be.service;

import com.ltm.be.dto.TopicDto;
import com.ltm.be.entity.TopicEntity;
import com.ltm.be.payload.request.TopicRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;

public interface ITopicService extends IBaseService<TopicDto, TopicEntity, Integer> {
    void create(TopicRequest request);
}
