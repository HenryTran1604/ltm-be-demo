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
@Table(name = "exam_user_exercise",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"exam_user_id", "exam_exercise_id"}
        ))
public class ExamUserExerciseEntity extends AbstractEntity<Long> {
    @Column(name = "ac")
    private boolean ac;

    @Column(name = "src_path")
    private String srcPath;

    @ManyToOne
    @JoinColumn(name = "alias_id")
    private AliasEntity alias;

    @ManyToOne
    @JoinColumn(name = "exam_user_id")
    private ExamUserEntity examUser;

    @ManyToOne
    @JoinColumn(name = "exam_exercise_id")
    private ExamExerciseEntity examExercise;

    @OneToMany(mappedBy = "examUserExercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamSubmissionEntity> examSubmissions;
}
