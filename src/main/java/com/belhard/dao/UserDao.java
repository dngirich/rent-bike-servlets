package com.belhard.dao;

import com.belhard.dao.exceptions.DaoException;
import com.belhard.domain.UserEntity;
import java.util.List;

public interface UserDao {
    
    UserEntity getUser(String login) throws DaoException;

    UserEntity getUser(String login, String password) throws DaoException;

    List<UserEntity> getAllUsers()throws DaoException;

    void updateUser(UserEntity user) throws DaoException;

    UserEntity getUserById(Integer userId)throws DaoException;

    void delete(Integer userId) throws DaoException;

    List<UserEntity> getAllSupports()throws DaoException;

    void addUser(UserEntity user) throws DaoException;
}
