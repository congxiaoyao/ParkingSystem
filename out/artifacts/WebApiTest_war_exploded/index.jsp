<%@ page import="com.congxiaoyao.beans.User" %>
<%--
  Created by IntelliJ IDEA.
  User: congxiaoyao
  Date: 2016/3/28
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>ParkingSystem</title>
  </head>
  <body>

  <%
      User user = (User) request.getSession().getAttribute("user");
      if (user != null) {
          response.sendRedirect(request.getContextPath() + "/parkingLot/parting.html");
      }else {
          response.sendRedirect(request.getContextPath() + "/login/login.html");
      }
  %>
  </body>
</html>
