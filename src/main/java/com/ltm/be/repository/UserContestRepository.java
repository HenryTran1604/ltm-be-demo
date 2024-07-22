package com.ltm.be.repository;

import com.ltm.be.entity.UserContestEntity;
import com.ltm.be.payload.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserContestRepository extends JpaRepository<UserContestEntity, Long> {
    List<UserContestEntity> findAllByContestId(Long contestId);
    Page<UserContestEntity> findAllByContestId(Long contestId, Pageable pageable);
    List<UserContestEntity> findAllByContestIdAndUserIdIn(Long contestId, List<Long> userIds);

    @Query(value = "SELECT * FROM user_contest WHERE user_id= ?1 AND contest_id = ?2", nativeQuery = true)
    Optional<UserContestEntity> findByUserIdAndContestId(Long userId, Long contestId);

    boolean existsByContestIdAndUserId(Long contestId, Long userId);
}
