package com.ltm.be.repository;

import com.ltm.be.entity.GroupEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupRepository extends BaseRepository<GroupEntity, UUID> {
}
