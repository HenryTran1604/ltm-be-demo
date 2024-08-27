package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class ExamDetailDto extends AbstractDto<UUID> {
    private UUID examId;
    private QuestionDto question;
    private Integer status;
    private Integer orderGroup;
}
