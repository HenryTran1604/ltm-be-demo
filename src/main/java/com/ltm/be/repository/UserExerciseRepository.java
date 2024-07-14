package com.ltm.be.repository;

import com.ltm.be.entity.UserExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExerciseRepository extends JpaRepository<UserExerciseEntity, Long> {
    List<UserExerciseEntity> findAllByUser_Id(Long userId);
}
