package com.ltm.be.repository;

import com.ltm.be.entity.ExamLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamLogRepository extends BaseRepository<ExamLogEntity, Long> {
    Page<ExamLogEntity> findAllByExamId(Long examId, Pageable pageable);
}
