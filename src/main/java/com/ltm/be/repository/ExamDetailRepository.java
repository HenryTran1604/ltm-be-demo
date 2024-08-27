package com.ltm.be.repository;

import com.ltm.be.entity.ExamDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExamDetailRepository extends org.springframework.data.jpa.repository.JpaRepository<ExamDetailEntity, UUID> {
    @Query(value = "SELECT exam_exercise.* FROM exam_exercise INNER JOIN exercise ON exam_exercise.exercise_id = exercise.id " +
            "AND exercise.topic_id = ?1", nativeQuery = true)
    List<ExamDetailEntity> findByExercise_TopicId(Long id);
//    Page<ExamExerciseEntity> findAllByExamId(Long examId, Pageable pageable);
//    List<ExamExerciseEntity> findAllByExamIdAndExerciseIdIn(Long examId, List<Long> exerciseIds);
}
