package com.ltm.be.repository;

import com.ltm.be.entity.ExamTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamTypeRepository extends BaseRepository<ExamTypeEntity, Integer> {
}
