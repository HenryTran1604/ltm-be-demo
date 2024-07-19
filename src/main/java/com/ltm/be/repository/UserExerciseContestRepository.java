package com.ltm.be.repository;

import com.ltm.be.entity.UserExerciseContestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExerciseContestRepository extends JpaRepository<UserExerciseContestEntity, Long> {
    Page<UserExerciseContestEntity> findAllByUserContest_User_Id(Long userId, Pageable pageable);
}
