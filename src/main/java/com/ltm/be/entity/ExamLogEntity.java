package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exam_log")
public class ExamLogEntity extends AbstractEntity<Long>{
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    @ManyToOne
    @JoinColumn(name = "exam_user_id")
    private ExamUserEntity examUser;
}
