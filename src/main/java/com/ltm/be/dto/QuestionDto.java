package com.ltm.be.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class QuestionDto extends AbstractDto<UUID> {
    private String name;
    private String code;
    private String content;
    private GroupDto group;
    private String subGroup;
    private Integer type; // ???
    private Integer status;
    private String solution;
    private String hint;
}
