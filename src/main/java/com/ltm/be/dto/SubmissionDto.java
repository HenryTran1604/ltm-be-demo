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
    private UserDto userDto;
    private ExerciseDto exerciseDto;
    private Integer AC;
    private Integer score;
    private LocalDateTime submittedAt;
    private String srcPath;
}
