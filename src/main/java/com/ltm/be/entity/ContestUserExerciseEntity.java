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
@Table(name = "contest_user_exercise",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"contest_user_id", "contest_exercise_id"}
        ))
public class ContestUserExerciseEntity extends AbstractEntity<Long> {
    @Column(name = "ac")
    private boolean ac;

    @Column(name = "src_path")
    private String srcPath;

    @ManyToOne
    @JoinColumn(name = "alias_id")
    private AliasEntity alias;

    @ManyToOne
    @JoinColumn(name = "contest_user_id")
    private ContestUserEntity contestUser;

    @ManyToOne
    @JoinColumn(name = "contest_exercise_id")
    private ContestExerciseEntity contestExercise;

    @OneToMany(mappedBy = "contestUserExercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContestSubmissionEntity> contestSubmissions;
}
