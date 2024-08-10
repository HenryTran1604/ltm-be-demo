package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PracticeLogDto extends AbstractDto<Long>{
    private Long userId;
    private String message;
    private String code;
    private String alias;
}
