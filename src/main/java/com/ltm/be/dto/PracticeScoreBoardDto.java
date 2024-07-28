package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PracticeScoreBoardDto extends AbstractScoreboardDto{
    private List<PracticeUserExerciseDto> practiceUserExercises;
}
