package com.belhard.service.impl;

import java.util.List;
import com.belhard.dao.UserDao;
import com.belhard.dao.factory.DaoFactory;
import com.belhard.service.UserService;
import com.belhard.dao.RentItemDao;
import com.belhard.domain.UserEntity;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static Logger log = Logger.getLogger(UserService.class);
    private UserDao userDao = DaoFactory.getFactory().getUserDao();
    private RentItemDao rentItemDao = DaoFactory.getFactory().getRentItemDao();

    @Override
    public UserEntity getUser(String login, String password) {
        UserEntity user = userDao.getUser(login, password);
        return user;
    }

  

    @Override
    public UserEntity getUser(String login) {
        UserEntity user = null;
        try {
            user = userDao.getUser(login);
        } catch (Exception e) {
            log.error(e);
        }
        return user;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteUser(Integer userId) {
        userDao.delete(userId);
    }

    @Override
    public UserEntity getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public void addUser(UserEntity user) {
        userDao.addUser(user);
    }


    @Override
    public void updateUser(UserEntity user) {
        userDao.updateUser(user);
    }

    @Override
    public List<UserEntity> getAllSupports() {
        return userDao.getAllSupports();
    }

}
