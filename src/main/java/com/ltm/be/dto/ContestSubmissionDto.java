package com.ltm.be.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContestSubmissionDto extends AbstractSubmissionDto {
    private ContestUserExerciseDto contestUserExercise;
}
