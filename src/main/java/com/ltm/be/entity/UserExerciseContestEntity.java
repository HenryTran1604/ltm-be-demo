package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_exercise_contest",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_contest_id", "exercise_contest_id"}
        ))
public class UserExerciseContestEntity extends AbstractEntity<Long>{
    @Column(name = "ac")
    private boolean ac;

    @Column(name = "src_path")
    private String srcPath;

    @ManyToOne
    @JoinColumn(name = "alias_id")
    private AliasEntity alias;

    @ManyToOne
    @JoinColumn(name = "user_contest_id")
    private UserContestEntity userContest;

    @ManyToOne
    @JoinColumn(name = "exercise_contest_id")
    private ExerciseContestEntity exerciseContest;

    @OneToMany(mappedBy = "userExerciseContest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubmissionEntity> submissions;
}
