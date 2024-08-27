package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class ExamUserDetailDto extends AbstractDto<UUID> {
    private UUID userExamId;
    private String alias;
    private boolean completed;
    private Integer attemptCount;
    private ExamDetailDto examDetail;
}
