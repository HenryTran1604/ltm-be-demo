package com.ltm.be.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ExamSubmissionDto extends AbstractDto<Long> {
    protected boolean ac;
    protected String srcPath;
    private ExamUserExerciseDto examUserExercise;
}
