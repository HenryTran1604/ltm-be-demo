package com.ltm.be.converter;

import com.ltm.be.dto.TopicDto;
import com.ltm.be.entity.TopicEntity;
import org.springframework.stereotype.Component;

@Component
public class TopicConverter {
    public TopicDto toDto(TopicEntity entity) {
        TopicDto dto = new TopicDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
