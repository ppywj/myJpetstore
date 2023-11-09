<%--
  Created by IntelliJ IDEA.
  User: 袁文靖
  Date: 2023-10-31
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 核心标签库-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 格式化标签库-->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 函数标签库-->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css" media="screen"/>
    <meta name="generator" content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org"/>
    <title>MyPetstore</title>
    <meta content="text/html; charset=windows-1252" http-equiv="Content-Type"/>
    <meta http-equiv="Cache-Control" content="max-age=0"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <style>
        #list {
            display: none;
            position: absolute;
            background-color: #fff;
            border: 1px solid #ccc;
            list-style: none;
            padding: 0;
        }

        #list li {
            height: 30px;
            line-height: 30px;
            cursor: pointer;
            padding: 0 10px;
        }

        #list li:hover {
            background-color: #f0f8ff; /* 浅蓝色 */
        }
    </style>
</head>
<c:out value="${fn:length(sessionScope.recommend.recommendList)}"/>
<body>
<div id="Header">
    <div id="Logo">
        <div id="LogoContent">
            <a href="${pageContext.request.contextPath}/index.html">
                <img src="images/logo-topbar.gif"/>
            </a>
        </div>
    </div>
    <div id="Menu">
        <div id="MenuContent">
            <a href="${pageContext.request.contextPath}/viewCart">
                <img align="middle" name="img_cart" src="images/cart.gif"/>
            </a>
            <img align="middle" src="images/separator.gif"/>
            <c:if test="${sessionScope.user == null}">
                <a href="${pageContext.request.contextPath}/login">Sign In</a>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <a href="${pageContext.request.contextPath}/signOut">Sign Out</a>
                <img align="middle" src="images/separator.gif"/>
                <a href="${pageContext.request.contextPath}/editAccount">My Account</a>
            </c:if>
            <img align="middle" src="images/separator.gif"/>
            <a href="${pageContext.request.contextPath}/help.html">?</a>
        </div>
    </div>
    <div id="Search">
        <div id="SearchContent">
            <form action="searchProduct" method="post">
                <input type="text" name="keyword" size="14" required onblur="recommend()"/>
                <input id="searchBtn" type="submit" name="searchBtn" value="Search"/>
            </form>
        </div>
        <!-- 推荐 -->
        <ul id="list"></ul>
    </div>
    <div id="QuickLinks">
        <a href="viewCategory?categoryId=FISH">
            <img src="images/sm_fish.gif"/>
        </a>
        <img src="images/separator.gif"/>
        <a href="viewCategory?categoryId=DOGS">
            <img src="images/sm_dogs.gif"/>
        </a>
        <img src="images/separator.gif"/>
        <a href="viewCategory?categoryId=REPTILES">
            <img src="images/sm_reptiles.gif"/>
        </a>
        <img src="images/separator.gif"/>
        <a href="viewCategory?categoryId=CATS">
            <img src="images/sm_cats.gif"/>
        </a>
        <img src="images/separator.gif"/>
        <a href="viewCategory?categoryId=BIRDS">
            <img src="images/sm_birds.gif"/>
        </a>
    </div>
</div>

<script>
    // 鼠标选中搜索框时显示列表
    document.querySelector('input[name="keyword"]').onfocus = function () {
        var recommends = [
            <c:forEach items="${sessionScope.recommend.recommendList}" var="item" varStatus="status">
            "${item}"${!status.last ? ',' : ''}
            </c:forEach>
        ];

        var list = document.getElementById('list');
        list.innerHTML = ''; // 清空列表
        for (var i = 0; i < recommends.length; i++) {
            var li = document.createElement('li');
            li.textContent = recommends[i];
            li.onclick = function () {
                document.querySelector('input[name="keyword"]').value = this.textContent;
                document.getElementById('list').style.display = 'none';
                // 模拟点击搜索按钮
                document.querySelector('button[name="searchBtn"]').click();
            };
            list.appendChild(li);
        }
        list.style.display = 'block';
        var searchInput = document.querySelector('input[name="keyword"]');
        var inputRect = searchInput.getBoundingClientRect(); // 获取输入框的位置信息
        var searchRecommendation = document.getElementById('list');
        searchRecommendation.style.top = inputRect.bottom + 'px';  // 将推荐列表定位到输入框底部
        searchRecommendation.style.left = inputRect.left + 'px';  // 将推荐列表与输入框左对齐
    }

    document.onclick = function (e) {
        if (e.target !== document.querySelector('input[name="keyword"]')) {
            document.getElementById('list').style.display = 'none';
        }
    };

</script>

<%--<script language="JavaScript">--%>

<%--    function recommend(){--%>
<%--    var recommend = ${sessionScope.recommend};--%>
<%--    var searchInput = document.querySelector('input[name="keyword"]');--%>
<%--    var searchRecommendation = document.getElementById('list');--%>
<%--    var searchBtn = document.getElementById('searchBtn');  // 假设搜索按钮的id为searchButton--%>
<%--        searchRecommendation.innerHTML = '';  // 清空内容--%>
<%--            var listItem = document.createElement('li');--%>
<%--            var itemName=recommend.recommendByCount1;--%>
<%--            console.log(itemName);--%>
<%--            listItem.textContent = itemName;  // 使用recommendItemName属性作为文本内容--%>
<%--            listItem.style.height = '30px';  // 设置每一项的高度为30像素--%>
<%--            listItem.style.lineHeight = '30px';  // 设置行高与高度相同，使得文字垂直居中--%>
<%--            listItem.style.backgroundColor = 'white';  // 设置背景颜色为白色--%>
<%--            listItem.style.color = 'black';  // 设置文字颜色为黑色--%>
<%--            listItem.style.borderRadius = '5px';  // 设置圆角--%>
<%--            listItem.addEventListener('mouseover', function() {--%>
<%--                this.style.backgroundColor = 'lightblue';  // 鼠标放上去时变为浅蓝色--%>
<%--            });--%>
<%--            listItem.addEventListener('mouseout', function() {--%>
<%--                this.style.backgroundColor = 'white';  // 鼠标移开时恢复白色--%>
<%--            });--%>
<%--            listItem.addEventListener('click', function() {--%>
<%--                searchInput.value = this.textContent;  // 将点击项的文本粘贴到搜索框中--%>
<%--                searchBtn.click();  // 触发搜索按钮的点击事件--%>
<%--            });--%>
<%--            searchRecommendation.appendChild(listItem);--%>
<%--        var inputRect = searchInput.getBoundingClientRect(); // 获取输入框的位置信息--%>
<%--        searchRecommendation.style.top = inputRect.bottom + 'px';  // 将推荐列表定位到输入框底部--%>
<%--        searchRecommendation.style.left = inputRect.left + 'px';  // 将推荐列表与输入框左对齐--%>
<%--        searchRecommendation.style.display = 'block';--%>
<%--    }--%>
<%--</script>--%>