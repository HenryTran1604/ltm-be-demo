package com.ltm.be.repository;

import com.ltm.be.entity.GroupEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupRepository extends org.springframework.data.jpa.repository.JpaRepository<GroupEntity, UUID> {
}
