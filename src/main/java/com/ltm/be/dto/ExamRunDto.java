package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ExamRunDto extends AbstractDto{
    private Long examId;
    private Long examUserId;
    private String content;
}
