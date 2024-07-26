package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractUserExerciseDto extends AbstractDto<Long>{
    protected String alias;
    protected boolean ac;
    protected String srcPath;
    protected Integer attemptCount;
}
