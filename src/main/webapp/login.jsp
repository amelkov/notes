<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Notes</title>
</head>
<body>
<h1>Login page</h1>
<div id="login-box">
  <c:if test="${not empty param.message}">
    <div>${param.message}</div>
  </c:if>
  <form action="Notes" method="post" name="loginForm">
    <INPUT type="hidden" name="command" value="login">
    <table>
      <tr>
        <td> Username:</td>
        <td><input type="text" name="login_user" id="login_user"></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="password_user" id="password_user"></td>
      </tr>
      <tr>
        <td colspan='2'><input type="submit" value="Submit"></td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>
