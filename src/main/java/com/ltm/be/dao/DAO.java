package com.ltm.be.dao;

import com.ltm.be.config.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
@Component
public class DAO {
    protected final String usersPath;
    protected final String exercisesPath;
    @Autowired
    public DAO(StorageProperties storageProperties) {
        this.usersPath = storageProperties.getUsersLocation();
        this.exercisesPath = storageProperties.getExercisesLocation();
    }
}
