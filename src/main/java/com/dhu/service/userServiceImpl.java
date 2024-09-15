package com.dhu.service;

import com.dhu.dao.BaseDao;
import com.dhu.dao.user.*;
import com.dhu.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class userServiceImpl implements userService {
    private userDao userDao ;
    public userServiceImpl(){
        userDao = new userDaoImpl();
    }
    @Override
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user =  userDao.getUserByName(connection,userCode);
            if(user==null||!user.getPassword().equals(password))
            {
                BaseDao.closeResource(connection,null,null);
                return null;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return user;
    }

    @Override
    public int register(String username, String password) throws SQLException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Connection connection = null;
        connection = BaseDao.getConnection();
        int result = userDao.registerUser(connection,user);
        return result;
    }

    @Override
    public ArrayList<User> getUserList() throws SQLException {
        Connection connection = null;
        connection = BaseDao.getConnection();
        ArrayList<User> userList = null;
        userList = userDao.getAllUsers(connection);
        return userList;
    }
}
