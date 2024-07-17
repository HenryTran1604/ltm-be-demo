package com.ltm.be.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserExerciseDto extends AbstractDto<Long> {
    private Long userId;
    private Integer exerciseId;
    private boolean ac;
    private Integer attemptCount;
}
