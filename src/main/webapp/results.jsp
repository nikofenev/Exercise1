<%@include file="head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html><body>

<%--TODO Pretty up the results!--%>
<div class="container-fluid">
    <h2>Search Results: </h2>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.userid}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.calculateAge()}</td>
        </tr><br />
    </c:forEach>
</div>
<br>

<div class="container-fluid">
    <form method="GET" action="searchUser">
        <input type="text" placeholder="enter last name" name="theSearchTerm"><br /><br />
        <input type="submit" value="Search">
    </form>
</div>

<div class="container-fluid">
    <c:forEach items="${searchResult}" var="user">
        <tr>
            <td>${user.userid}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.calculateAge()}</td>
        </tr><br />
    </c:forEach>
</div>

</body>
</html>
