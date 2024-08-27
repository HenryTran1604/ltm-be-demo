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
@Table(name = "\"user\"")
public class UserEntity extends AbstractEntityWithAuditor<UUID>{
    @Column(name = "user_name")
    private String userName;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // note
    private List<ExamUserEntity> examUsers;

    // needn't OneToMany for logs
}
