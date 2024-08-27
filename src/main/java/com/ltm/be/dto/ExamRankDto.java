package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class ExamRankDto extends AbstractDto<UUID> {
    private String ip;
    private Integer score;
    private String username;
    private Long examUserId;
    private List<ExamUserDetailDto> examUserExercises;
}
