<%--
  Created by IntelliJ IDEA.
  User: 袁文靖
  Date: 2023-10-31
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 核心标签库-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 格式化标签库-->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 函数标签库-->
<%@ include file="../common/IncludeTop.jsp"%>

${sessionScope.message}

<%@ include file="../common/IncludeBottom.jsp"%>