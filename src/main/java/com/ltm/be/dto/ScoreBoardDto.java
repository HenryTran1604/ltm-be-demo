package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class ScoreBoardDto {
    private Long id;
    private String username;
    private String ip;
    private LocalDateTime createdAt;
    private Integer score;
    private List<UserExerciseContestDto> userExerciseContests;
}
