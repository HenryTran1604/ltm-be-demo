package com.ltm.be.repository;

import com.ltm.be.entity.PracticeLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeLogRepository extends JpaRepository<PracticeLogEntity, Long> {
    Page<PracticeLogEntity> findAllByUserId(Long userId, Pageable pageable);
}
