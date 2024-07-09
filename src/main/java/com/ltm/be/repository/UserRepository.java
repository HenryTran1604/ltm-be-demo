package com.ltm.be.repository;

import com.ltm.be.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAll();
    UserEntity findByStudentCode(String studentCode);
    UserEntity findByStudentCodeAndIp(String studentCode, String ip);
    boolean existsByStudentCode(String studentCode);
    boolean existsByStudentCodeAndIp(String studentCode, String ip);
}
