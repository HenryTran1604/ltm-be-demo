package com.ltm.be.repository;

import com.ltm.be.entity.ClientLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientLogRepository extends JpaRepository<ClientLogEntity, Long> {
}
