package com.ltm.be.repository;

import com.ltm.be.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
}
