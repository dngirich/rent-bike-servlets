package com.belhard.service;

import com.belhard.domain.UserEntity;
import java.util.List;

public interface UserService {

    UserEntity getUser(String login, String password);

    UserEntity getUser(String login);

    List<UserEntity> getAllUsers();

    void deleteUser(Integer userId);

    UserEntity getUserById(Integer userId);

    void addUser(UserEntity user);

    void updateUser(UserEntity user);

    List<UserEntity> getAllSupports();
}
