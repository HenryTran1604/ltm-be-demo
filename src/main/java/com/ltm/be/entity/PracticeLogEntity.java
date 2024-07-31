package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "practice_log")
public class PracticeLogEntity extends AbstractEntity<Long>{
    @Column(name = "alias")
    private String alias;
    @Column(name = "code")
    private Integer code;
    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
