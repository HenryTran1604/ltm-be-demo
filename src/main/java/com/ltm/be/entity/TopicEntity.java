package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topic")
public class TopicEntity extends AbstractEntity<Integer>{
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExerciseEntity> exercises;
}
