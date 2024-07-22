package com.ltm.be.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionDto extends AbstractDto<Long> {
    private boolean ac;
    private String srcPath;
    private UserExerciseContestDto userExerciseContest;
}
