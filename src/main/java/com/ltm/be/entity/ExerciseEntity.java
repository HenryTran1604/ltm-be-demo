package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise")
public class ExerciseEntity extends AbstractEntity<Long>{
    @Column(name = "name")
    private String name;

    @Column(name = "alias")
    private String alias;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    @OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExerciseContestEntity> exerciseContests;
}
