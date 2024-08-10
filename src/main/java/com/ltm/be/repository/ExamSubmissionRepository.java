package com.ltm.be.repository;

import com.ltm.be.entity.ExamSubmissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExamSubmissionRepository extends BaseRepository<ExamSubmissionEntity, Long> {
    Page<ExamSubmissionEntity> findAllByExamUserExercise_examUserUserId(Long userId, Pageable pageable);

}