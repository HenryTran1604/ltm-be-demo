package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exam")
public class ExamEntity extends AbstractEntityWithAuditor<UUID> {
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamTypeEntity examType;

    @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamUserEntity> examUsers;

    @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamDetailEntity> examDetails;
    // needn't OneToMany for logs
}
