<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Naldo
  Date: 10/01/2017
  Time: 01:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Welcome page</title>
</head>
<body>
Dear <strong>${user}</strong>, Welcome to Home Page.
<a href="<c:url value="/logout" />">Logout</a>

<br/>
<br/>

<div>
    <label>View all information| This part is visible to Everyone</label>
</div>

<br/>

<div>
    <sec:authorize access="hasRole('ADMIN')">
        <label><a href="#">Edit this page</a> | This part is visible only to ADMIN</label>
    </sec:authorize>
</div>

<br/>

<div>
    <sec:authorize access="hasRole('ADMIN') and hasRole('DBA')">
        <label><a href="#">Start backup</a> | This part is visible only to one who is both ADMIN & DBA</label>
    </sec:authorize>
</div>

</body>
</html>
