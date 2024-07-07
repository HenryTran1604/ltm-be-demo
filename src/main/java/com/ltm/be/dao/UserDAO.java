package com.ltm.be.dao;

import com.ltm.be.config.StorageProperties;
import com.ltm.be.entity.UserEntity;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Component
@NoArgsConstructor
public class UserDAO{
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    protected StorageProperties storageProperties;

    public UserEntity saveUser(UserEntity userEntity) {
        List<UserEntity> UserEntities = readUsersFromFile();
        UserEntities.add(userEntity);
        writeUsersToFile(UserEntities);
        return userEntity;
    }

    public UserEntity getUserById(String id) {
        List<UserEntity> users = getAllUsers();
        for(UserEntity user : users) {
            if(user.getId().equalsIgnoreCase(id)) {
                return user;
            }
        }
        return null;
    }
    public List<UserEntity> getAllUsers() {
        return readUsersFromFile();
    }

    public void updateUser(UserEntity updatedUserEntity) {
        List<UserEntity> UserEntities = readUsersFromFile();
        for (int i = 0; i < UserEntities.size(); i++) {
            UserEntity UserEntity = UserEntities.get(i);
            if (UserEntity.getIp().equals(updatedUserEntity.getIp())) {
                UserEntities.set(i, updatedUserEntity);
                writeUsersToFile(UserEntities);
                return;
            }
        }
        throw new RuntimeException("UserEntity not found: " + updatedUserEntity.getId());
    }

    private void writeUsersToFile(List<UserEntity> UserEntities) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.storageProperties.getUsersLocation()))) {
            oos.writeObject(UserEntities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<UserEntity> readUsersFromFile() {
        Resource usersResource = resourceLoader.getResource(this.storageProperties.getUsersLocation());

        if (!usersResource.exists()) {
            return new ArrayList<>();
        }

        try (InputStream is = usersResource.getInputStream();
             ObjectInputStream ois = new ObjectInputStream(is)) {
            return (List<UserEntity>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
