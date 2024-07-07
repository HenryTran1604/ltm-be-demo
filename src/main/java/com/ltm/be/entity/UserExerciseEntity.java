package com.ltm.be.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExerciseEntity implements Serializable {
    private int id;
    private int tryCount;
    private boolean AC;
    public UserExerciseEntity(int id) {
        this.id = id;
        this.tryCount = 0;
        this.AC = false;
    }
}
