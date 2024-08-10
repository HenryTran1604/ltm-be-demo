package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ExamUserExerciseDto extends AbstractDto<Long> {
    private Long userExamId;
    private String alias;
    private boolean ac;
    private String srcPath;
    private Integer attemptCount;
    private ExamExerciseDto examExercise;
}
