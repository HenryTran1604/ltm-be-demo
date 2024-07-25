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
@Table(name = "alias")
public class AliasEntity extends AbstractEntity<Long>{
    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @OneToMany(mappedBy = "alias")
    private Set<UserExerciseContestEntity> userExerciseContests = new HashSet<>();
}
