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
@Table(name = "contest_exercise",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"contest_id", "exercise_id"}
        ))
public class ContestExerciseEntity extends AbstractEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestEntity contest;
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;
    @OneToMany(mappedBy = "contestExercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContestUserExerciseEntity> contestUserExercises;


}
