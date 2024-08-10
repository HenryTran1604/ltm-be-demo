package com.ltm.be.converter;

import com.ltm.be.dto.TopicDto;
import com.ltm.be.entity.TopicEntity;
import org.springframework.stereotype.Component;

@Component
public class TopicConverter extends AbstractBaseConverter<TopicDto, TopicEntity> {
    public TopicDto toDto(TopicEntity entity) {
        return TopicDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
