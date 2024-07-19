package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "submission")
public class SubmissionEntity extends AbstractEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ac")
    private boolean ac;

    @Column(name = "src_path")
    private String srcPath;

    @ManyToOne
    @JoinColumn(name = "user_exercise_contest_id", nullable = false)
    private UserExerciseContestEntity userExerciseContest;

}
