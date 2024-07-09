package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserExerciseDto {
    private Long userId;
    private Long exerciseId;
    private Integer AC;
    private Integer attemptCount;
}
