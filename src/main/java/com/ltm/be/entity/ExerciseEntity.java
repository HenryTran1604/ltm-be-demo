package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise")
public class ExerciseEntity extends AbstractEntity<Integer>{
    @Column(name = "name")
    private String name;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;
}
