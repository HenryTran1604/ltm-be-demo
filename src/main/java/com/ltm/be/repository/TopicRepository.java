package com.ltm.be.repository;

import com.ltm.be.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends BaseRepository<TopicEntity, Integer> {
}
