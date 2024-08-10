package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exam_submission")
public class ExamSubmissionEntity extends AbstractEntity<Long> {
    @Column(name = "ac")
    private boolean ac;
    @Column(name = "src_path")
    private String srcPath;
    @ManyToOne
    @JoinColumn(name = "exam_user_exercise_id", nullable = false)
    private ExamUserExerciseEntity examUserExercise;

}
