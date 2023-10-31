<%--
  Created by IntelliJ IDEA.
  User: 袁文靖
  Date: 2023-10-31
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>


<h3>Account Information</h3>

<table>
    <tr>
        <td>First name:</td>
        <td>
            <input type="text" name="firstName" value="${sessionScope.user.firstName}"/>
        </td>
    </tr>
    <tr>
        <td>Last name:</td>
        <td>
            <input type="text" name="lastName" value="${sessionScope.user.lastName}"/>
        </td>
    </tr>
    <tr>
        <td>Email:</td>
        <td><input type="text" size="40" name="email" value="${sessionScope.user.email}"/></td>
    </tr>
    <tr>
        <td>Phone:</td>
        <td><input type="text" name="phone" value="${sessionScope.user.phone}"/></td>
    </tr>
    <tr>
        <td>Address 1:</td>
        <td><input type="text" size="40" name="address1" value="${sessionScope.user.address1}"/></td>
    </tr>
    <tr>
        <td>Address 2:</td>
        <td><input type="text" size="40" name="address2" value="${sessionScope.user.address2}"/></td>
    </tr>
    <tr>
        <td>City:</td>
        <td><input type="text" name="city" value="${sessionScope.user.city}"/></td>
    </tr>
    <tr>
        <td>State:</td>
        <td><input type="text" size="4" name="state" value="${sessionScope.user.state}"/></td>
    </tr>
    <tr>
        <td>Zip:</td>
        <td><input type="text" size="10" name="zip" value="${sessionScope.user.zip}"/></td>
    </tr>
    <tr>
        <td>Country:</td>
        <td><input type="text" size="15" name="country" value="${sessionScope.user.country}"/></td>
    </tr>
</table>

<h3>Profile Information</h3>

<table>
    <tr>
        <td>Language Preference:</td>
        <td>
            <select name="languagePreference" id="languagePreference">
                <c:forEach var="languages" items="${sessionScope.languages}">
                    <option>${languages}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>Favourite Category:</td>
        <td>
            <select name="favouriteCategoryId" id="favouriteCategoryId">
                <c:forEach var="categories" items="${sessionScope.categories}">
                    <option>${categories}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>Enable MyList</td>
        <td><input type="checkbox" name="listOption" value="listOption"/></td>
    </tr>
    <tr>
        <td>Enable MyBanner</td>
        <td><input type="checkbox" name="bannerOption" value="bannerOption"/></td>
    </tr>

</table>
