package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alias")
public class AliasEntity extends AbstractEntity<Long>{
    @Column(name = "code")
    private String code;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @OneToMany(mappedBy = "alias")
    private Set<ContestUserExerciseEntity> userExerciseContests = new HashSet<>();
}
