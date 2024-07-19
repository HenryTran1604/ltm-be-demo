package com.ltm.be.service.impl;

import com.ltm.be.entity.TopicEntity;
import com.ltm.be.payload.request.TopicRequest;
import com.ltm.be.repository.TopicRepository;
import com.ltm.be.service.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements ITopicService {
    private final TopicRepository topicRepository;
    @Override
    public void addTopic(TopicRequest request) {
        TopicEntity entity = TopicEntity.builder()
                .name(request.getName())
                .build();
        topicRepository.save(entity);
    }
}
