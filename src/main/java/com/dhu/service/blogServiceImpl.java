package com.dhu.service;

import com.dhu.dao.BaseDao;
import com.dhu.dao.blog.blogDao;
import com.dhu.dao.blog.blogImpl;
import com.dhu.pojo.lytable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class blogServiceImpl implements blogService {
    blogDao bl = new blogImpl();

    @Override
    public List<Map<String, Object>> getAllblogs() {
        Connection connection = null;
        List<Map<String, Object>> ls = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            ls = bl.getAllUserBlogs(connection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return ls;
    }

    @Override
    public int submitBlog(lytable blog, int userId) {
        Connection connection = null;
        int result = 0;
        try {
            connection = BaseDao.getConnection();
            result = bl.submit_blog(blog, connection, userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getUserBlog(int userId) {
        Connection connection = null;
        List<Map<String, Object>> ls = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            ls = bl.getUserBlogs(connection,userId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return ls;
    }
}
