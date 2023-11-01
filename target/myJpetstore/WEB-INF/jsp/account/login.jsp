<%--
  Created by IntelliJ IDEA.
  User: 袁文靖
  Date: 2023-10-31
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>

<%--
  Created by IntelliJ IDEA.
  User: 袁文靖
  Date: 2023-10-31
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 核心标签库-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 格式化标签库-->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 函数标签库-->
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>登录</title>
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 40px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            display: flex;               /* 添加此行 */
            flex-direction: column;      /* 添加此行 */
            justify-content: center;     /* 添加此行 */
            align-items: center;         /* 添加此行 */
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label,
        input {
            display: block;
            width: 100%;
        }

        label {
            margin-bottom: 5px;
            font-weight: bold;
            color: #666;
        }

        input[type="text"],
        input[type="password"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .btn-group {
            text-align: center;
        }

        .btn {
            display: inline-block;
            padding: 8px 16px;
            font-size: 14px;
            text-align: center;
            cursor: pointer;
            color: #fff;
            background-color: #4CAF50;
            border: none;
            border-radius: 3px;
        }

        .btn-reset {
            background-color: #ccc;
        }

        .form-group.checkbox {
            display: flex;
            align-items: center;
        }

        .form-group.checkbox label {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<a href="Main" >return to Menu</a>
<div class="container">
    <h2>登录</h2>
    <form action="${pageContext.request.contextPath}/login" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="account">账号</label>
            <input type="text" id="account" name="account" value="${cookie.user.account}" required>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" id="password" name="password" value="" required><br>
        </div>
        <div class="form-group">
            <label>验证码:</label>
            <span style="display: flex; align-items: center;">
                <input type="text" name="checkCode">
                <img alt="验证码" src="${pageContext.request.contextPath}/ValidateCodeServlet" style="margin-left: 10px;">
            </span>
        </div>

        <div class="btn-group">
            <input type="submit" value="登录" class="btn">
            <input type="reset" value="重置" class="btn btn-reset">
            <a href="${pageContext.request.contextPath}/register" class="register-link">没有账号？点击注册</a>
        </div>
    </form>
    <p id="error-message" class="error-message"></p>
    <script>
        function validateForm() {
            const username = document.getElementById("account").value;
            const password = document.getElementById("password").value;
            const checkCode = document.getElementsByName("checkCode")[0].value;

            if (username.trim() === "" || password.trim() === "" || checkCode.trim() === "") {
                alert("账号、密码或验证码不能为空");
                return false;
            }
            return true;
        }

        const error = "${errorMsg}";
        if (error === '1') {
            alert("验证码错误")
        } else if (error === '2') {
            alert("账号或密码错误")
        }

    </script>
</div>

</body>
</html>