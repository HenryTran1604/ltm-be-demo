package com.ltm.be.repository;

import com.ltm.be.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends AbstractEntity<R>, R> extends JpaRepository<T, R> {
}
