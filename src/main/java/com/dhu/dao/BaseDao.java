package com.dhu.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    static{
        //通过类加载器读取资源
        InputStream inputStream = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try{
            properties.load(inputStream);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }
    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
    //编写查询公共类
    public static ResultSet excute(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet,String sql,Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i+1,params[i]);

        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    public static int excute(Connection connection,PreparedStatement preparedStatement,String sql,Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i+1,params[i]);

        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }
    public static boolean closeResource(Connection connection ,PreparedStatement preparedStatement,ResultSet resultSet)
    {
        boolean flag = true;
        if(resultSet!=null){
            try{
                resultSet.close();
                resultSet = null;
            }catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        if(connection!=null){
            try{
                connection.close();
                connection = null;
            }catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        if(preparedStatement!=null){
            try{
                preparedStatement.close();
                preparedStatement = null;
            }catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
