package com.ltm.be.repository;

import com.ltm.be.entity.ContestSubmissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContestSubmissionRepository extends JpaRepository<ContestSubmissionEntity, Long> {
    Page<ContestSubmissionEntity> findAllByContestUserExercise_ContestUserUserId(Long userId, Pageable pageable);

}