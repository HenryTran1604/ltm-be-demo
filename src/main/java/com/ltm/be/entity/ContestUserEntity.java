package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contest_user",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"contest_id", "user_id"}
        ))
public class ContestUserEntity extends AbstractEntity<Long>{
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestEntity contest;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToMany(mappedBy = "contestUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ContestUserExerciseEntity> contestUserExercises;
}
