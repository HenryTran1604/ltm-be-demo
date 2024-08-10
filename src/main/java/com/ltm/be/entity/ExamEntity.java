package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exam")
public class ExamEntity extends AbstractEntity<Long>{
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamTypeEntity examType;

    @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamUserEntity> examUsers;
    @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamExerciseEntity> examExercises;
    // needn't OneToMany for logs
}
