<%--
  Created by IntelliJ IDEA.
  User: 周继勇
  Date: 2024/6/26
  Time: 下午8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>留言板系统</title>
    <link href="public/loginStyle.css" rel="stylesheet">
<%--    <script type="application/javascript" src="public/login.js"></script>--%>
</head>
<body>
    <div class="box">
        <div class="left">
            <img src="public/logo_login.jpg" alt="logo">
        </div>
        <div class="right_login">
            <h1>Login</h1>
            <form action="${pageContext.request.contextPath}/login.do" name="actionForm" id="loginForm" method="get" >
                <input type="hidden" name="method" value="login">
                <input type="text" name="userName" class="inputItem" placeholder="Enter your userName">
                <input type="password" name="userPassword" class="inputItem" placeholder="Enter your passWord">
                <div class="info">${error}</div>
                <a href="#"  class="to_register">register?</a>
                <button class="btn" id="login_button" type="submit">Login</button>
            </form>
        </div>
        <div class="right_register">
            <h1>Register</h1>
            <div class="register">
                <input type="text" class="inputItem" id="regUsername" placeholder="Enter your userName">
                <input type="password" class="inputItem" id = "regFirstPwd" placeholder="Enter your passWord">
                <input type="password" class="inputItem" id="regSecondPwd" placeholder="Repeat your passWord">
                <div class="reg_error"></div>
                <a href="#" class="to_login">Login!</a>
                <button class="btn" id="register_button" type="button">Register</button>
            </div>

        </div>
    </div>
    <script type="application/javascript" src="public/login.js"></script>
    <script type="application/javascript" src="public/jquery-1.8.3.min.js"></script>
    <script type="application/javascript" src="public/common.js"></script>
</body>
</html>
