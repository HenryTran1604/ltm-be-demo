package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exam_run")
public class ExamRunEntity extends AbstractEntity<Long> {
    @Column(name = "status")
    private Integer status;

    @Column(name = "process_log")
    private String processLog;

    @ManyToOne
    @JoinColumn(name = "exam_user_id")
    private ExamUserEntity examUser;
}
