package com.ltm.be.repository;

import com.ltm.be.entity.AliasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AliasRepository extends BaseRepository<AliasEntity, UUID> {
    boolean existsByCodeAndActive(String code, boolean active);
}
