package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contest_submission")
public class ContestSubmissionEntity extends AbstractEntity<Long> {
    @Column(name = "ac")
    private boolean ac;
    @Column(name = "src_path")
    private String srcPath;
    @ManyToOne
    @JoinColumn(name = "contest_user_exercise_id", nullable = false)
    private ContestUserExerciseEntity contestUserExercise;

}
