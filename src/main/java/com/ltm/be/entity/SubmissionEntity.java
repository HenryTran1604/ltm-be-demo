package com.ltm.be.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionEntity implements Serializable {
    private UserEntity user;
    private ExerciseEntity exercise;
    private boolean AC;
    private LocalDateTime submittedAt;
}
