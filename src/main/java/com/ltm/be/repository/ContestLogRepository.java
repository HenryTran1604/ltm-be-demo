package com.ltm.be.repository;

import com.ltm.be.entity.ContestLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestLogRepository extends JpaRepository<ContestLogEntity, Long> {
    Page<ContestLogEntity> findAllByContestIdAndContestUser_UserId(Long contestId, Long userId, Pageable pageable);
    Page<ContestLogEntity> findAllByContestId(Long contestId, Pageable pageable);
}
