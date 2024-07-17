package com.ltm.be.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto extends AbstractDto<Integer> {
    private String name;
    private String content;
}
