package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ExamUserDetailDto extends AbstractDto{
    private Long userExamId;
    private String alias;
    private boolean ac;
    private String srcPath;
    private Integer attemptCount;
    private ExamDetailDto examExercise;
}
