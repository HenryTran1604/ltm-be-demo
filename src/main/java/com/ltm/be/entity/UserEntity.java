package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"user\"",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username", "ip"})
        })
public class UserEntity extends AbstractEntity<Long> {
    @Column(name = "username")
    private String username;

    @Column(name = "ip")
    private String ip;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // note
    private List<ContestUserEntity> contestUsers;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PracticeUserExerciseEntity> practiceUserExercises;
    // needn't OneToMany for logs
}
