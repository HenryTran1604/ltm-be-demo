package com.ltm.be.repository;

import com.ltm.be.entity.ContestUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContestUserRepository extends JpaRepository<ContestUserEntity, Long> {
    List<ContestUserEntity> findAllByContestId(Long contestId);
    Page<ContestUserEntity> findAllByContestId(Long contestId, Pageable pageable);
    List<ContestUserEntity> findAllByContestIdAndUserIdIn(Long contestId, List<Long> userIds);

    @Query(value = "SELECT * FROM user_contest WHERE user_id= ?1 AND contest_id = ?2", nativeQuery = true)
    Optional<ContestUserEntity> findByUserIdAndContestId(Long userId, Long contestId);

    boolean existsByContestIdAndUserId(Long contestId, Long userId);
}
