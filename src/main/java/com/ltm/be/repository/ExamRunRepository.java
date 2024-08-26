package com.ltm.be.repository;

import com.ltm.be.entity.ExamRunEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRunRepository extends BaseRepository<ExamRunEntity, Long> {
    Page<ExamRunEntity> findAllByExamId(Long examId, Pageable pageable);
}
