package com.ltm.be.repository;

import com.ltm.be.entity.SubmissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {
    Page<SubmissionEntity> findAllByUserExerciseContest_UserContestUserId(Long userId, Pageable pageable);

}