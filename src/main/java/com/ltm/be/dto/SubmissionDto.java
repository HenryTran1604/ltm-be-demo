package com.ltm.be.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SubmissionDto extends AbstractDto<Long>{
    private String name;
    private String src;
    private Integer type;
    private Integer status;
    private String clientInfo;
    private String path;
    private boolean pass;
}
