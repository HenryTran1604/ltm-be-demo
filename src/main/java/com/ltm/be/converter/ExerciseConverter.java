package com.ltm.be.converter;

import com.ltm.be.dto.QuestionDto;
import com.ltm.be.entity.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseConverter extends AbstractBaseConverter<QuestionDto, QuestionEntity> {
    private final TopicConverter topicConverter;

    public QuestionDto toDto(QuestionEntity entity) {
        return QuestionDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .topic(topicConverter.toDto(entity.getGroup()))
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
