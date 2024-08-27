package com.ltm.be.repository;

import com.ltm.be.entity.ExamUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExamUserRepository extends org.springframework.data.jpa.repository.JpaRepository<ExamUserEntity, UUID> {
    List<ExamUserEntity> findAllByExamId(Long examId);
    Page<ExamUserEntity> findAllByExamId(Long examId, Pageable pageable);
    List<ExamUserEntity> findAllByExamIdAndUserIdIn(Long examId, List<Long> userIds);
    @Query(value = "SELECT * FROM user_exam WHERE user_id= ?1 AND exam_id = ?2", nativeQuery = true)
    Optional<ExamUserEntity> findByUserIdAndExamId(Long userId, Long examId);
    boolean existsByExamIdAndUserId(Long examId, Long userId);
}
