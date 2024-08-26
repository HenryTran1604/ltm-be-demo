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
@Table(name = "exercise")
public class QuestionEntity extends AbstractEntity<UUID>{
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "level")
    private Integer level;

    @Column(name = "sub_group")
    private String subGroup;

    @Column(name = "type")
    private Integer type; // ???

    @Column(name = "status")
    private Integer status;

    @Column(name = "solution")
    private String solution;

    @Column(name = "hint")
    private String hint;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamDetailEntity> examDetails;
}
