package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Getter
@Setter
@SuperBuilder
public class ExamRankDto extends AbstractDto{
    private String ip;
    private Integer score;
    private String username;
    private Long examUserId;
    private List<ExamUserDetailDto> examUserExercises;
}
