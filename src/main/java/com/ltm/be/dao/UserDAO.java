package com.ltm.be.dao;

import com.ltm.be.config.StorageProperties;
import com.ltm.be.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class UserDAO extends DAO{

    public UserDAO(StorageProperties storageProperties) {
        super(storageProperties);
    }
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.usersPath))) {
            oos.writeObject(UserEntities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<UserEntity> readUsersFromFile() {
        File file = new File(this.usersPath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.usersPath))) {
            return (List<UserEntity>) ois.readObject();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
