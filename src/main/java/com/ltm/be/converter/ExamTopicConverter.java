package com.ltm.be.converter;

import com.ltm.be.entity.ExamTopicEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamTopicConverter extends AbstractBaseConverter<ExamTopicDto, ExamTopicEntity> {

    @Override
    public ExamTopicDto toDto(ExamTopicEntity entity) {
        return ExamTopicDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .examId(entity.getExam().getId())
                .build();
    }
}
