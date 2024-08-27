package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "submission")
public class SubmissionEntity extends AbstractEntityWithAuditor<UUID>{
    @Column(name = "name")
    private String name;

    @Column(name = "src")
    private String src;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private String size;

    @Column(name = "status")
    private Integer status;

    @Column(name = "test_info")
    private String testInfo;
    
    @Column(name = "pass")
    private Boolean pass;

    @ManyToOne
    @JoinColumn(name = "exam_user_detail_id", nullable = false)
    private ExamUserDetailEntity examUserExercise;

}
