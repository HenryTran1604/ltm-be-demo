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
@Table(name = "exam_user",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"exam_id", "user_id"}
        ))
public class ExamUserEntity extends AbstractEntity<Long>{
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToMany(mappedBy = "examUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamUserExerciseEntity> examUserExercises;
    @OneToMany(mappedBy = "examUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamLogEntity> examLogs;
}
