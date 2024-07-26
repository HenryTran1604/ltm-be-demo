package com.ltm.be.repository;

import com.ltm.be.entity.ContestUserExerciseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestUserExerciseRepository extends JpaRepository<ContestUserExerciseEntity, Long> {
    Page<ContestUserExerciseEntity> findAllByContestUser_User_Id(Long userId, Pageable pageable);
}
