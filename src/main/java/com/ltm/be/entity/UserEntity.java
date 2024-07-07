package com.ltm.be.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
    @NonNull
    private String id;
    @NonNull
    private String ip;
    private LocalDateTime createdAt;
    private List<SubmissionEntity> exerciseList;
}
