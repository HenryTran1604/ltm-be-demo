package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "practice_user_exercise")
public class PracticeUserExerciseEntity extends AbstractEntity<Long> {
    @Column(name = "ac")
    private boolean ac;

    @Column(name = "src_path")
    private String srcPath;

    @ManyToOne
    @JoinColumn(name = "alias_id")
    private AliasEntity alias;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @OneToMany(mappedBy = "practiceUserExercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PracticeSubmissionEntity> practiceSubmissions;

}
