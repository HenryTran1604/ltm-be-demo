package com.ltm.be.repository;

import com.ltm.be.entity.SubmissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface SubmissionRepository extends BaseRepository<SubmissionEntity, UUID> {
    Page<SubmissionEntity> findAllByExamUserExercise_examUserUserId(Long userId, Pageable pageable);

}