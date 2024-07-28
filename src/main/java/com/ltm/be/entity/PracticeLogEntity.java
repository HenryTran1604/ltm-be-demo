package com.ltm.be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "practice_log")
public class PracticeLogEntity extends AbstractEntity<Long>{
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
