package com.ltm.be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
public class AbstractEntityWithAuditor<T> extends AbstractEntity<T> {

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "last_modified_at")
    @LastModifiedDate
    private OffsetDateTime lastModifiedAt;

    @Column(name = "last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;
}
