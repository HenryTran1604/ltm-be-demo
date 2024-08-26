package com.ltm.be.repository;

import com.ltm.be.entity.QuestionEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExerciseRepository extends BaseRepository<QuestionEntity, UUID> {
}
