package com.ltm.be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client_log")
public class ClientLogEntity extends AbstractEntity<Long>{
    private String content;
}
