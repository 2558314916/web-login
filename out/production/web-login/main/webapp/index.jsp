<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>
    <%
        Object s = request.getAttribute("title");
        String display;
        if (s == null) {
            display = "Hello Login";
        } else {
            display = "注册完成 登录";
        }
    %>
    <%=display%>
</h1>
<br/>
<form action="login">
    <label>
        <input type="text" name="username" placeholder="登录账号">
        <input type="password" name="password" placeholder="登录密码">
    </label>
    <input type="submit" value="登录">
</form>
<form action="register.jsp">
    <input type="submit" value="注册">
</form>
</body>
</html>