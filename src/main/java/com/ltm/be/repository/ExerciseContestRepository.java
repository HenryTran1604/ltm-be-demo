package com.ltm.be.repository;

import com.ltm.be.entity.ExerciseContestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseContestRepository extends JpaRepository<ExerciseContestEntity, Long> {
    @Query(value = "SELECT exercise_contest.* FROM exercise_contest INNER JOIN exercise ON exercise_contest.exercise_id = exercise.id " +
            "AND exercise.topic_id = ?1", nativeQuery = true)
    List<ExerciseContestEntity> findByExercise_TopicId(Integer id);
    Optional<ExerciseContestEntity> findByExerciseId(Long exerciseId);
}
