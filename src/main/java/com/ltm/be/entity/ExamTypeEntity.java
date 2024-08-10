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
@Table(name = "exam_type")
public class ExamTypeEntity extends AbstractEntity<Integer>{
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "examType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExamEntity> exams;
}
