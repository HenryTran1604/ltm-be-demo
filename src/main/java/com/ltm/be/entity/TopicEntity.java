package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topic")
public class TopicEntity extends AbstractEntity<Integer>{
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ExerciseEntity> exercises = new HashSet<>();
}
