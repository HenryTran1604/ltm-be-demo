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
@Table(name = "exam_type")
public class ExamTypeEntity extends AbstractEntity<UUID>{
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "examType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExamEntity> exams;
}
