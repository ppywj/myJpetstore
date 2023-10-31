<%--
  Created by IntelliJ IDEA.
  User: 袁文靖
  Date: 2023-10-31
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>

<div id="Footer">

    <div id="PoweredBy">&nbsp;<a href="http://www.mybatis.org">www.mybatis.org</a>
    </div>

    <div id="Banner"><c:if test="${sessionScope.accountBean != null }">
        <c:if test="${sessionScope.accountBean.authenticated}">
            <c:if test="${sessionScope.accountBean.user.bannerOption}">
                ${sessionScope.accountBean.user.bannerName}
            </c:if>
        </c:if>
    </c:if></div>

</div>

</body>
</html>
