package com.dhu.servlet;
import com.alibaba.fastjson2.JSONArray;
import com.dhu.Constants;
import com.dhu.pojo.User;
import com.dhu.service.blogService;
import com.dhu.service.userServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.dhu.service.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import com.dhu.pojo.lytable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String method = req.getParameter("method");
       if(method.equals("getALLBlog")){
        getALLBlog(req, resp);
       }
       else if(method.equals("submitBlog")){
           submitBlog(req, resp);
       }
       else if(method.equals("getUserBlog")){
           getUserBlog(req, resp);
       }else if(method.equals("getUsers")){
           try {
               getUsers(req,resp);
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }
    }
    protected void getALLBlog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        blogService blogService = new blogServiceImpl();
        List<Map<String,Object>> RESULTLIST = blogService.getAllblogs();
        req.setAttribute("RESULT", RESULTLIST);
        req.getSession().setAttribute("RESULT", RESULTLIST);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(JSONArray.toJSONString(RESULTLIST));
        out.flush();
        out.close();
    }
    protected void submitBlog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        blogService blogService = new blogServiceImpl();
        User user = (User)req.getSession().getAttribute(Constants.USER_SESSION);
        int userId = user.getId();
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        lytable lytable = new lytable();
        lytable.setTitle(title);
        lytable.setContent(content);
        int reuslt = blogService.submitBlog(lytable,userId);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        HashMap<String,Integer>map = new HashMap<>();
        if(reuslt == 1){
            map.put("result", 1);
        }
        else{
            map.put("result", 0);
        }
        out.write(JSONArray.toJSONString(map));
        out.flush();
        out.close();
//        req.getRequestDispatcher("blog.jsp").forward(req, resp);
    }
    protected void getUserBlog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        blogService blogService = new blogServiceImpl();
        int userId = Integer.parseInt(req.getParameter("userId"));
        List<Map<String,Object>> RESULTLIST = blogService.getUserBlog(userId);
        req.setAttribute("RESULT", RESULTLIST);
        req.getSession().setAttribute("RESULT", RESULTLIST);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(JSONArray.toJSONString(RESULTLIST));
        out.flush();
        out.close();
//        req.getRequestDispatcher("blog.jsp").forward(req, resp);
    }
    protected void getUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        userService userService = new userServiceImpl();
        ArrayList<User> users = new ArrayList<>();
        users = userService.getUserList();
        req.setAttribute("usersList", users);
        req.getSession().setAttribute("usersList", users);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(JSONArray.toJSONString(users));
        out.flush();
        out.close();

//        req.getRequestDispatcher("blog.jsp").forward(req, resp);
    }
}
