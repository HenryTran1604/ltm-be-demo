package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractScoreboardDto extends AbstractDto<Long>{
    protected String ip;
    protected Integer score;
    protected String username;
}
