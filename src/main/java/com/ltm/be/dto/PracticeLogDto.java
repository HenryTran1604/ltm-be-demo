package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PracticeLogDto extends AbstractDto<Long>{
    private Long userId;
    private String message;
    private String code;
    private String alias;
}
