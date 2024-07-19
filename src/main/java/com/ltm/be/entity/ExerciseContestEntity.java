package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise_contest",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"contest_id", "exercise_id"}
        ))
public class ExerciseContestEntity extends AbstractEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestEntity contest;
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;
    @OneToMany(mappedBy = "exerciseContest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserExerciseContestEntity> userExerciseContests = new HashSet<>();


}
