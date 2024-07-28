package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client_log")
public class ContestLogEntity extends AbstractEntity<Long>{
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestEntity contest;

    @ManyToOne
    @JoinColumn(name = "contest_user_id")
    private ContestUserEntity contestUser;
}
