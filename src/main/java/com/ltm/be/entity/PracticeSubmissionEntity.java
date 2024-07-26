package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "practice_submission")
public class PracticeSubmissionEntity extends AbstractEntity<Long> {
    @Column(name = "ac")
    private boolean ac;

    @ManyToOne
    @JoinColumn(name = "practice_user_exercise_id")
    private PracticeUserExerciseEntity practiceUserExercise;
}
