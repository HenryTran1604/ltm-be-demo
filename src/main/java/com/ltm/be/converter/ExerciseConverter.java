package com.ltm.be.converter;

import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.entity.ExerciseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseConverter extends AbstractBaseConverter<ExerciseDto, ExerciseEntity> {
    private final TopicConverter topicConverter;

    public ExerciseDto toDto(ExerciseEntity entity) {
        return ExerciseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .topic(topicConverter.toDto(entity.getTopic()))
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
