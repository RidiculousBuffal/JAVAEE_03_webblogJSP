package com.dhu.dao.user;

import com.dhu.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface userDao {
    public User getUserByName(Connection connection,String userName) throws SQLException;
    public int registerUser(Connection connection,User user) throws SQLException;
    public ArrayList<User> getAllUsers(Connection connection) throws SQLException;
}
