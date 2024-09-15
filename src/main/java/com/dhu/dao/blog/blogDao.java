package com.dhu.dao.blog;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dhu.pojo.lytable;
public interface blogDao {
    List<Map<String,Object>> getAllUserBlogs(Connection conn) throws SQLException;
    int submit_blog(lytable lytable,Connection conn,int userId) throws SQLException;
    List<Map<String,Object>> getUserBlogs(Connection conn,int userId) throws SQLException;
}
