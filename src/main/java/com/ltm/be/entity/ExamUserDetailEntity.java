package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exam_user_exercise")
public class ExamUserDetailEntity extends AbstractEntityWithAuditor <UUID>{
    @Column(name = "status")
    private boolean completed; // status: AC, NOT AC

    @ManyToOne
    @JoinColumn(name = "alias_id")
    private AliasEntity alias;

    @ManyToOne
    @JoinColumn(name = "exam_user_id")
    private ExamUserEntity examUser;

    @ManyToOne
    @JoinColumn(name = "exam_detail_id")
    private ExamDetailEntity examDetail;

    @OneToMany(mappedBy = "examUserDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubmissionEntity> submissions;
}
