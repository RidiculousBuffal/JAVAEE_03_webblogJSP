package com.dhu.dao.blog;

import com.dhu.dao.BaseDao;
import com.dhu.pojo.User;
import com.dhu.pojo.lytable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class blogImpl implements blogDao{
    @Override
    public  List<Map<String,Object>> getAllUserBlogs(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String,Object>> resultList = new ArrayList<>();
        lytable lt = null;
        if(conn!=null){
            String sql = "select lytable.id,lytable.userId,lytable.dates,lytable.title,lytable" +
                ".content,user.username from lytable,user where " +
                "user.id = " +
                "lytable" +
                ".userId";
            System.out.println(sql);
            rs = BaseDao.excute(conn,ps,rs,sql,new Object[]{});
            while(rs.next()){
                Map<String,Object> map = new HashMap<>();
                String ly_id = rs.getString("content");
                String ly_title = rs.getString("title");
                Date ly_dates = rs.getDate("dates");
                String ly_content = rs.getString("content");
                String ly_username = rs.getString("username");
                map.put("id",ly_id);
                map.put("title",ly_title);
                map.put("dates",ly_dates);
                map.put("content",ly_content);
                map.put("username",ly_username);
                System.out.println(ly_id);
                System.out.println(ly_title);
                System.out.println(ly_dates);
                System.out.println(ly_content);
                System.out.println(ly_username);
                System.out.println();System.out.println();System.out.println();System.out.println();
                resultList.add(map);
            }
            return resultList;
        }
        return null;
    }
    public int submit_blog(lytable lytable,Connection conn,int userId) throws SQLException{
        PreparedStatement preparedStatement = null;
        int result = 0 ;
        if(conn!=null){
            String sql = "insert into lytable(content,title,dates,userId) values(?,?,NOW(),?)";
            String content = lytable.getContent();
            String title = lytable.getTitle();
            Object[] params = new Object[]{content,title,userId};
            result = BaseDao.excute(conn,preparedStatement,sql,params);
            return result;
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getUserBlogs(Connection conn, int userId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String,Object>> resultList = new ArrayList<>();
        lytable lt = null;
        if(conn!=null){
            String sql = "select lytable.id,lytable.userId,lytable.dates,lytable.title,lytable" +
                ".content,user.username from lytable,user where " +
                "user.id = " +
                "lytable" +
                ".userId and userId = ?";
            System.out.println(sql);
            rs = BaseDao.excute(conn,ps,rs,sql,new Object[]{userId});
            while(rs.next()){
                Map<String,Object> map = new HashMap<>();
                String ly_id = rs.getString("content");
                String ly_title = rs.getString("title");
                Date ly_dates = rs.getDate("dates");
                String ly_content = rs.getString("content");
                String ly_username = rs.getString("username");
                map.put("id",ly_id);
                map.put("title",ly_title);
                map.put("dates",ly_dates);
                map.put("content",ly_content);
                map.put("username",ly_username);
                System.out.println(ly_id);
                System.out.println(ly_title);
                System.out.println(ly_dates);
                System.out.println(ly_content);
                System.out.println(ly_username);
                System.out.println();System.out.println();System.out.println();System.out.println();
                resultList.add(map);
            }
            return resultList;
        }
        return null;
    }
}
