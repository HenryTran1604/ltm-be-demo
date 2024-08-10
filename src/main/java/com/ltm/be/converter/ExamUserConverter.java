package com.ltm.be.converter;

import com.ltm.be.dto.ExamUserDto;
import com.ltm.be.entity.ExamUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamUserConverter extends AbstractBaseConverter<ExamUserDto, ExamUserEntity>{
    private final ExamConverter examConverter;
    private final UserConverter userConverter;

    public ExamUserDto toDto(ExamUserEntity entity) {
        return ExamUserDto.builder()
                .user(userConverter.toDto(entity.getUser()))
                .exam(examConverter.toDto(entity.getExam()))
                .build();
    }
}
