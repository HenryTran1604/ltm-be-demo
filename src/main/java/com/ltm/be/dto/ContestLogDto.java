package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContestLogDto extends AbstractDto<Long>{
    private Long contestId;
    private Long contestUserId;
    private String content;
}
