package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exam_user")
public class ExamUserEntity extends AbstractEntity<UUID>{
    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "examUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamUserDetailEntity> examUserExercises;

    @OneToMany(mappedBy = "examUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamRunEntity> examRuns;
}
