package com.ltm.be.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class ExerciseDto extends AbstractDto<Long> {
    private String name;
    private String content;
    private TopicDto topic;
}
