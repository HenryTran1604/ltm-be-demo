package com.ltm.be.repository;

import com.ltm.be.entity.ContestExerciseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContestExerciseRepository extends JpaRepository<ContestExerciseEntity, Long> {
    @Query(value = "SELECT contest_exercise.* FROM contest_exercise INNER JOIN exercise ON contest_exercise.exercise_id = exercise.id " +
            "AND exercise.topic_id = ?1", nativeQuery = true)
    List<ContestExerciseEntity> findByExercise_TopicId(Integer id);
    Optional<ContestExerciseEntity> findByExerciseId(Long exerciseId);

    Page<ContestExerciseEntity> findAllByContestId(Long contestId, Pageable pageable);

    List<ContestExerciseEntity> findAllByContestIdAndExerciseIdIn(Long contestId, List<Long> exerciseIds);
}
