package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserExerciseContestDto extends AbstractDto<Long> {
    private Long userContestId;
    private String alias;
    private ExerciseContestDto exerciseContest;
    private boolean ac;
    private String srcPath;
    private Integer attemptCount;
}
