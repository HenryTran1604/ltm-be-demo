package com.ltm.be.repository;

import com.ltm.be.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, UUID> {
    List<UserEntity> findAll();
    Optional<UserEntity> findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByUserNameAndIpAddress(String username, String ipAddress);

}
