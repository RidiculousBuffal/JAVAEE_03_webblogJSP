package com.dhu.servlet;
import com.alibaba.fastjson2.JSONArray;
import com.dhu.Constants;
import com.dhu.pojo.User;
import com.dhu.service.userServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.dhu.service.userService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method.equals("login")){
            login(req, resp);
        }
        if(method.equals("register")){
            try {
                register(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Login Servlet....");
        String userCode = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");

        System.out.println(userCode);
        System.out.println(userPassword);

        //调用业务层
        userService userService = new userServiceImpl();
        User user = userService.login(userCode, userPassword);//查出登陆的人
        System.out.println(user);
        if(user!=null)//查出此人
        {
            //放入SESSION中
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            //跳转到内部主页
            resp.sendRedirect("blog.jsp");
        }else{
            //无法登陆,转发回登录页面
            req.setAttribute("error","用户名密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("Register Servlet....");
        String userCode = req.getParameter("username");
        String userPassword = req.getParameter("password");
        userService userService = new userServiceImpl();
        int result = userService.register(userCode,userPassword);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        if(result!=1){
            map.put("regResult",0);
        }else{
            map.put("regResult",1);
        }
        PrintWriter out = resp.getWriter();
        out.write(JSONArray.toJSONString(map));
        out.flush();
        out.close();
    }
}
