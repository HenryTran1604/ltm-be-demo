package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exam_exercise",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"exam_id", "exercise_id"}
        ))
public class ExamExerciseEntity extends AbstractEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;
    @OneToMany(mappedBy = "examExercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamUserExerciseEntity> examUserExercises;


}
