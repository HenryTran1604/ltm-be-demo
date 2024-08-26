package com.ltm.be.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alias")
public class AliasEntity extends AbstractEntityWithAuditor<UUID> {
    @Column(name = "name", nullable = false)
    @Size(min = 7, max = 8)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;
}
