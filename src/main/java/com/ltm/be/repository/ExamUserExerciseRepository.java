package com.ltm.be.repository;

import com.ltm.be.entity.ExamUserExerciseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamUserExerciseRepository extends BaseRepository<ExamUserExerciseEntity, Long> {
    Page<ExamUserExerciseEntity> findAllByExamUser_User_Id(Long userId, Pageable pageable);
}
