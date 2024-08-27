package com.ltm.be.repository;

import com.ltm.be.entity.ExamUserDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExamUserDetailRepository extends org.springframework.data.jpa.repository.JpaRepository<ExamUserDetailEntity, UUID> {
    Page<ExamUserDetailEntity> findAllByExamUser_User_Id(Long userId, Pageable pageable);
}
