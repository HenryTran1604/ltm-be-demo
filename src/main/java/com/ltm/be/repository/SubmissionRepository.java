package com.ltm.be.repository;

import com.ltm.be.entity.SubmissionEntity;
import com.ltm.be.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {
    List<SubmissionEntity> findAllByUser_Id(Long userId);

    Integer countByUser_IdAndExercise_Id(Long userId, Long exerciseId);

//    @Query(value =
//            "SELECT * FROM submission " +
//            "WHERE user_id = ?1 AND exercise_id = ?2 " +
//            "ORDER BY Id LIMIT 1",
//            nativeQuery = true)
//    SubmissionEntity findLastSubmissionByUserAndExercise(Long userId, Long exerciseId);

    @Query(value =
            "SELECT submission.* FROM exercises LEFT JOIN submission" +
                    "ON submission.exercise_id = exercise.id" +
                    "ORDER BY Id LIMIT 1",
            nativeQuery = true)
    List<SubmissionEntity> findAllLastSubmissionsByUserAndAllExercises(Long userId);

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO submission (user_id, exercise_id, submitted_at, ac, src_path) " +
                    "Values (?1, ?2, ?3, ?4, ?5)",
            nativeQuery = true)
    void addByUserIdAndExerciseId(Long userID, Long exerciseId, LocalDateTime submittedAt, Integer AC, String srcPath);
    boolean existsByUserIdAndExerciseIdAndAc(Long userId, Long exerciseId, Integer ac);
}