package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ScoreBoardDto {
    private Long id;
    private Long userContestId;
    private String username;
    private String ip;
    private Integer score;
    private List<ContestUserExerciseDto> contestUserExercises;
}
