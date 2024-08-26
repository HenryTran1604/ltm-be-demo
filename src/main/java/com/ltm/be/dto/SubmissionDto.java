package com.ltm.be.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SubmissionDto extends AbstractDto{
    protected boolean ac;
    protected String srcPath;
    private ExamUserDetailDto examUserExercise;
}
