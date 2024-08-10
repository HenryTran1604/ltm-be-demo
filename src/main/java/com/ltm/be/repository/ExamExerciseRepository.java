package com.ltm.be.repository;

import com.ltm.be.entity.ExamExerciseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamExerciseRepository extends BaseRepository<ExamExerciseEntity, Long> {
    @Query(value = "SELECT exam_exercise.* FROM exam_exercise INNER JOIN exercise ON exam_exercise.exercise_id = exercise.id " +
            "AND exercise.topic_id = ?1", nativeQuery = true)
    List<ExamExerciseEntity> findByExercise_TopicId(Integer id);
    Page<ExamExerciseEntity> findAllByExamId(Long examId, Pageable pageable);
    List<ExamExerciseEntity> findAllByExamIdAndExerciseIdIn(Long examId, List<Long> exerciseIds);
}
