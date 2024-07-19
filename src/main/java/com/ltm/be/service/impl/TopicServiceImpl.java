package com.ltm.be.service.impl;

import com.ltm.be.converter.TopicConverter;
import com.ltm.be.entity.TopicEntity;
import com.ltm.be.payload.request.TopicRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.TopicRepository;
import com.ltm.be.service.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements ITopicService {
    private final TopicRepository topicRepository;
    private final TopicConverter topicConverter;
    @Override
    public void addTopic(TopicRequest request) {
        TopicEntity entity = TopicEntity.builder()
                .name(request.getName())
                .build();
        topicRepository.save(entity);
    }

    @Override
    public PageResponse<?> getAllTopics(int pageNo, int pageSize) {
        int page = 0;
        if(pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<TopicEntity> topicPages = topicRepository.findAll(pageable);
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(topicPages.getTotalPages())
                .totalElements(topicPages.getTotalElements())
                .items(topicPages.stream().map(topicConverter::toDto).toList())
                .build();
    }
}
