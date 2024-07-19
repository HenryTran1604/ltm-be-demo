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
@Table(name = "user_contest")
public class UserContestEntity extends AbstractEntity<Long>{
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestEntity contest;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToMany(mappedBy = "userContest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserExerciseContestEntity> userExerciseContests = new HashSet<>();
}
