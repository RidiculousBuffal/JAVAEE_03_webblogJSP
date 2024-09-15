package com.dhu.dao.user;

import com.dhu.dao.BaseDao;
import com.dhu.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class userDaoImpl implements userDao {
    @Override
    public User getUserByName(Connection connection, String userName) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        if (connection != null) {
            String sql = "select * from user where username = ?";
            Object[] params = {userName};

            resultSet = BaseDao.excute(connection, preparedStatement, resultSet, sql, params);
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            BaseDao.closeResource(null, preparedStatement, resultSet);
            return user;
        }
        return null;
    }

    @Override
    public int registerUser(Connection connection, User user) throws SQLException {
        User user_request = getUserByName(connection, user.getUsername());
        if (user_request != null) {
            return -1;
        } else {
            PreparedStatement preparedStatement = null;
            int result = 0;
            if (connection != null) {
                String sql = "insert into user (username,password) values(?,?)";
                String username = user.getUsername();
                String password = user.getPassword();
                Object[] params = {username, password};
                result = BaseDao.excute(connection, preparedStatement, sql, params);
            }
            return result;
        }

    }

    @Override
    public ArrayList<User> getAllUsers(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        ArrayList<User> users = new ArrayList<>();
        if (connection != null) {
            String sql = "select id,username from user ";
            resultSet = BaseDao.excute(connection, preparedStatement, resultSet, sql, new Object[]{});
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                users.add(user);
            }
            BaseDao.closeResource(null, preparedStatement, resultSet);
            return users;
        }
        return null;
    }
}
