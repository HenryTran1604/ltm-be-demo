package com.ltm.be.repository;

import com.ltm.be.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);

    boolean existsByName(String roleAdmin);
}
