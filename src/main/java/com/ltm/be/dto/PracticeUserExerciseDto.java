package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PracticeUserExerciseDto extends AbstractUserExerciseDto{
    private Long userId;
    private ExerciseDto exercise;
}
