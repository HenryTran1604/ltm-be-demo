package com.ltm.be.repository;

import com.ltm.be.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAll();
    Optional<UserEntity> findByStudentCode(String studentCode);
    Optional<UserEntity> findByStudentCodeAndIp(String studentCode, String ip);
    boolean existsByStudentCode(String studentCode);
    boolean existsByStudentCodeAndIp(String studentCode, String ip);
}
