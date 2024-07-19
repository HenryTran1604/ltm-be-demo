package com.ltm.be.controller;

import com.ltm.be.payload.request.TopicRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.ITopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
@Tag(name = "Topic Controller")
public class TopicController {
    private final ITopicService topicService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> addTopic(@RequestBody TopicRequest request) {
        topicService.addTopic(request);
        return new ResponseData<>(HttpStatus.CREATED.value(),
                "Add Topic successfully!");
    }
    @GetMapping("/all")
    public ResponseData<?> getAllTopics(@RequestParam(defaultValue = "0") int pageNo,
                                        @RequestParam(defaultValue = "30") int pageSize) {

        return new ResponseData<>(HttpStatus.OK.value(),
                "Topics",
                topicService.getAllTopics(pageNo, pageSize));
    }
}
