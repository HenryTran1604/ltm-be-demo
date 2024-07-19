package com.ltm.be.service;

import com.ltm.be.payload.request.TopicRequest;
import com.ltm.be.payload.response.PageResponse;

public interface ITopicService {
    void addTopic(TopicRequest request);

    PageResponse<?> getAllTopics(int pageNo, int pageSize);
}
