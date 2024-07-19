package com.ltm.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Builder
@Entity
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
    private Set<UserContestEntity> userContest = new HashSet<>();
}
