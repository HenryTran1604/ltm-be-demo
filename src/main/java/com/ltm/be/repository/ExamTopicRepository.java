package com.ltm.be.repository;

import com.ltm.be.entity.ExamTopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ExamTopicRepository extends BaseRepository<ExamTopicEntity, UUID> {
    Page<ExamTopicEntity> findAllByExamId(Long examId, Pageable pageable);
}
