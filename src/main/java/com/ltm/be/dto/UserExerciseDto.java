package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExerciseDto {
    private int id;
    private int tryCount;
    private boolean AC;
}
