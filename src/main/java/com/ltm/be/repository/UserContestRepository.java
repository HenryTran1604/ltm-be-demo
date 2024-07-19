package com.ltm.be.repository;

import com.ltm.be.entity.UserContestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserContestRepository extends JpaRepository<UserContestEntity, Long> {
    List<UserContestEntity> findAllByContestId(Long contestId);
    Optional<UserContestEntity> findByUserId(Long userId);

    @Query(value = "SELECT * FROM user_contest WHERE user_id= ?1 AND contest_id = ?2", nativeQuery = true)
    Optional<UserContestEntity> findByUserIdAndContestId(Long userId, Long contestId);
}
