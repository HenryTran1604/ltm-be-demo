package com.ltm.be.repository;

import com.ltm.be.entity.ContestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends JpaRepository<ContestEntity, Long> {
}
