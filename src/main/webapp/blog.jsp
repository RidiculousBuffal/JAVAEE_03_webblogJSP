<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 周继勇
  Date: 2024/6/26
  Time: 下午11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>留言板</title>
    <link href="public/blog.css" rel="stylesheet">
    <%--    <script type="application/javascript" src="public/login.js"></script>--%>
    <script type="application/javascript" src="public/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="wrap">
    <div>
        <h1 class="title"> Filters </h1>

        <div class="byUser">
            <span class="title">All post by User : </span>
            <select id="selUser" name="selUser">
                <option value=0 selected="selected">All</option>
                <c:forEach var="user" items="${usersList}">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </select>
            <button class="btn" type="button" id="search" >Search</button>
            <button class="btn" type="button" id="subBTN">Submit a new Post!</button>
        </div>
    </div>

</div>

<div class="wrap">
    <c:forEach var="blog" items="${RESULT}" varStatus="status">
        <div class="blogs">
            <h1>
                    ${blog.title}
            </h1>
            <p>
                    ${blog.content}
            </p>
            <hr>
            <p class="author-info">
                By ${blog.username} ---- <fmt:formatDate value="${blog.dates}" pattern="yyyy-MM-dd"/>
            </p>

        </div>
    </c:forEach>
</div>
<dialog id="submitDialog">
    <h1>
        您正在以 <span id="user">${userSession.username}</span> 的身份发布留言
    </h1>
    <hr/>
    <form method="post" action=${pageContext.request.contextPath}/submit.do name="submitForm" id="submitForm">
        <input type="hidden" name="method" value="submitBlog">
        <input type="hidden" name="userid" value="${userSession.id}">
        <p style="font-size: 20px;">Input your title!</p>
        <input type="text" name="title" id="title">

        <p style="font-size: 20px;">
            Input your content!
        </p>

        <textarea name="content" id="blogContent">

                </textarea>
        <button name="submit" class="btn" type="submit">submit!</button>
        <button name="cancel" class="btn" id="cancel_submit" type="button">Cancel</button>
    </form>
</dialog>
<script type="application/javascript" src="public/blog.js"></script>
<script type="application/javascript" src="public/jquery-1.8.3.min.js"></script>
<script type="application/javascript" src="public/common.js"></script>
</body>
</html>
