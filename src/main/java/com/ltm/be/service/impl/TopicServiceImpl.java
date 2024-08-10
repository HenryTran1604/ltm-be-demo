package com.ltm.be.service.impl;

import com.ltm.be.converter.AbstractBaseConverter;
import com.ltm.be.converter.TopicConverter;
import com.ltm.be.dto.TopicDto;
import com.ltm.be.entity.TopicEntity;
import com.ltm.be.payload.request.TopicRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.BaseRepository;
import com.ltm.be.repository.TopicRepository;
import com.ltm.be.service.ITopicService;
import com.ltm.be.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl extends BaseServiceImpl<TopicDto, TopicEntity, Integer> implements ITopicService {
    public TopicServiceImpl(TopicRepository topicRepository,
                            TopicConverter topicConverter) {
        super(topicRepository, topicConverter);
    }

    @Override
    public void create(TopicRequest request) {
        TopicEntity entity = TopicEntity.builder()
                .name(request.getName())
                .build();
        create(entity);
    }

}
