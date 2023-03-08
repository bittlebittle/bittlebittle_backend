<%--
  Created by IntelliJ IDEA.
  User: s
  Date: 2023/03/07
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <h1>메인입니다.</h1>
  <h3>유저등록</h3>
  <form action="api/users" method="post">
    <table>
      <tr>
        <td>유저 아이디 : </td>
        <td><input type="text" name="userId"></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" value="전송"></td>
      </tr>
    </table>
  </form>
  <a href="api/users">ddd</a>
  <a href="  <%= request.getContextPath() %>/start">start</a>
  <%= request.getContextPath() %>
  </body>
</html>
