package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contest")
public class ContestEntity extends AbstractEntity<Long>{
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "contest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserContestEntity> userContests;
    @OneToMany(mappedBy = "contest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ExerciseContestEntity> exerciseContests;
}
