package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
    private Set<ContestUserExerciseEntity> contestUserExercises;


}
