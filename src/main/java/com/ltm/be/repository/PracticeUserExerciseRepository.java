package com.ltm.be.repository;

import com.ltm.be.entity.PracticeUserExerciseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeUserExerciseRepository extends JpaRepository<PracticeUserExerciseEntity, Long> {
    Page<PracticeUserExerciseEntity> findAllByUserId(Long userId, Pageable pageable);
}
