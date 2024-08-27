package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topic")
public class GroupEntity extends AbstractEntityWithAuditor<UUID>{
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionEntity> exercises;
}
