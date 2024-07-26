package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractSubmissionDto extends AbstractDto<Long>{
    protected boolean ac;
    protected String srcPath;
}
