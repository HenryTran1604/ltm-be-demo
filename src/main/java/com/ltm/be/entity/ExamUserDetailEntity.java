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
@Table(name = "exam_user_exercise",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"exam_user_id", "exam_exercise_id"}
        ))
public class ExamUserDetailEntity extends AbstractEntity <UUID>{
    @Column(name = "status")
    private Integer status;

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
