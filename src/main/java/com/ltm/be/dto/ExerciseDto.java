package com.ltm.be.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto extends AbstractDto<Long> {
    private String name;
    private String content;
    private TopicDto topic;
}
