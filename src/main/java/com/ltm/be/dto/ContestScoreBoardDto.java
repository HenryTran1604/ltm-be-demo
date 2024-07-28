package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ContestScoreBoardDto extends AbstractScoreboardDto{
    private Long userContestId;
    private List<ContestUserExerciseDto> contestUserExercises;
}
