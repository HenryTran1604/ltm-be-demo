package com.ltm.be.repository;

import com.ltm.be.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExamRepository extends BaseRepository<ExamEntity, UUID> {
}
