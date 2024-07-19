package com.ltm.be.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto extends AbstractDto<Long> {
    private String name;
    private String alias;
    private String content;
    private TopicDto topic;
}
