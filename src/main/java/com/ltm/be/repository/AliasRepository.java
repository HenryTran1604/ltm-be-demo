package com.ltm.be.repository;

import com.ltm.be.entity.AliasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AliasRepository extends JpaRepository<AliasEntity, Long> {
    List<AliasEntity> findByCodeIn(Set<String> names);
}
