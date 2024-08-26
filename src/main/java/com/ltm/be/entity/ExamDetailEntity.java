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
@Table(name = "exam_exercise")
public class ExamDetailEntity extends AbstractEntityWithAuditor<UUID> {
    @Column(name = "status")
    private Integer status;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "order_group")
    private Integer orderGroup;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    @OneToMany(mappedBy = "examDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamUserDetailEntity> examUserDetails;
}
