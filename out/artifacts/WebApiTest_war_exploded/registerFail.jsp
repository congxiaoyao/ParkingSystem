<%--
  Created by IntelliJ IDEA.
  User: congxiaoyao
  Date: 2016/5/17
  Time: 1:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <META http-equiv="refresh" content="0;URL=../signUp/signUp.html">
    <title>注册失败</title>
</head>
<body>

<script type="text/javascript">
    var content =
    <%
        System.out.print(request.getAttribute("reason"));
        out.print("\'"+request.getAttribute("reason")+"\'"+";");
    %>
    alert(content);
</script>

</body>
</html>
