package com.ltm.be.repository;

import com.ltm.be.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAll();
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByUsernameAndIp(String username, String ip);
    boolean existsByUsername(String username);
    boolean existsByUsernameAndIp(String username, String ip);
}
