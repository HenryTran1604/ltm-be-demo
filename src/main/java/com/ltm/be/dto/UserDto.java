package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String ip;
    private LocalDateTime createdAt;
    private int score;
    private List<ExerciseDto> exerciseList;
}
