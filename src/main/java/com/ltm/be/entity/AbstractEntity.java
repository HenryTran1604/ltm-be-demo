package com.ltm.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp // tự động thêm sẽ gây vấn đề nếu server được đặt ở một nơi không phải Việt Nam
                        // Tạm thời bỏ qua
    @Temporal(TemporalType.TIMESTAMP)
    protected LocalDateTime createdAt;
}
