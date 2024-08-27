package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exam_topic")
public class ExamTopicEntity extends AbstractEntityWithAuditor<UUID> {
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    @OneToMany(mappedBy = "examTopic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamDetailEntity> examExercises;
}
