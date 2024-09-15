package com.dhu.service;

import com.dhu.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface userService {
    public User login  (String userCode, String password);
    public int register (String username, String password) throws SQLException;
    public ArrayList<User> getUserList() throws SQLException;
}
