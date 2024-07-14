package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionDto {
    private Long id;
    private UserExerciseDto userExerciseDto;
    private boolean ac;
    private LocalDateTime createdAt;
    private String srcPath;
}
