package com.ltm.be.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserEntity implements Serializable {
    @NonNull
    private String id;
    @NonNull
    private String ip;
    private LocalDateTime createdAt;
    private List<UserExerciseEntity> exercises;
    public UserEntity() {
        this.exercises = new ArrayList<>();
        for(int i = 1; i <= 4; i++) {
            this.exercises.add(new UserExerciseEntity(i));
        }
    }
}
