
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 核心标签库-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 格式化标签库-->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 函数标签库-->
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
        display: flex; /* 添加此行 */
        flex-direction: column; /* 添加此行 */
        justify-content: center; /* 添加此行 */
        align-items: center; /* 添加此行 */
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
<!-- 引入jquery -->
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 引入axios cdn依赖-->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<a href="Main">return to Menu</a>
<div class="container">
    <form action="editAccountAction" method="post">

        <h3>User Information</h3>

        <table>
            <tr>
                <td>User ID:</td>
                <td>${sessionScope.user.username}</td>
            </tr>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" id="password" name="password" value="" required><br>
            </div>

            <div class="form-group">
                <label for="repeatedPassword">确认密码</label>
                <td><input type="password" name="repeatedPassword" id="repeatedPassword" required
                           onblur="checkRepeatPwd()"/></td>
                <div id="pwdTip"></div>
            </div>
        </table>
        <%@ include file="IncludeAccountFields.jsp" %>
        <input type="submit" name="editAccount" value="Save Account Information"/>
    </form>
    <a href="orderList">My Orders</a>
</div>
<script>
    function checkRepeatPwd() {
        var inputPwd = $('#repeatedPassword').val(); // 重复密码
        var newPwd = $('#password').val();//新密码
        if(inputPwd===''||newPwd==='')
            return;
        //使axios发送http请求
        if (inputPwd === newPwd) {
            $('#pwdTip').html('\u4e24\u6b21\u8f93\u5165\u7684\u5bc6\u7801\u4e0d\u4e00\u81f4').css('color', 'red');
        } else {
            $('#pwdTip').html('\u4e24\u6b21\u8f93\u5165\u5bc6\u7801\u4e00\u81f4').css('color', 'green');
        }
    }
</script>
<%@ include file="../common/IncludeBottom.jsp" %>
