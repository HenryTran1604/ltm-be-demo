package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class RoleEntity extends AbstractEntity<Integer>{
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<UserEntity> users;
}
