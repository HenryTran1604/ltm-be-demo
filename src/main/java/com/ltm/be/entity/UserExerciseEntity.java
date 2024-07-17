package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_exercise",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "exercise_id"})})
public class UserExerciseEntity extends AbstractEntity<Long>{

    @Column(name = "ac")
    private boolean ac;

    @Column(name = "src_path")
    private String srcPath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @OneToMany(mappedBy = "userExercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubmissionEntity> submissions;
}
