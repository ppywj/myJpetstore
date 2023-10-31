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
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"  isELIgnored="false" %>


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

</head>
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
                <input type="text" name="keyword" size="14"/>
                <input type="submit" name="searchProducts" value="Search"/>
            </form>
        </div>
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