package com.ltm.be.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class QuestionDto extends AbstractDto<UUID> {
    private String name;
    private String content;
    private GroupDto group;
}
