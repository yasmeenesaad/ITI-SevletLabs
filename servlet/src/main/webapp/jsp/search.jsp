<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Database Access</title>
</head>
<body>
    <h2>Database Access Example</h2>

    <!-- Set up database connection -->
    <sql:setDataSource var="myDataSource"
        driver="com.mysql.cj.jdbc.Driver"
        url="jdbc:mysql://localhost:3366/mydb"
        user="root"
        password="4520" />

    <!-- Query the database -->
    <sql:query var="result" dataSource="${myDataSource}">
        SELECT username, password FROM login WHERE username = ?
        <sql:param value="${param.name}" />
    </sql:query>

    <!-- Display the results -->
    <c:choose>
        <c:when test="${not empty result.rows}">
            <table border="1">
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                </tr>
                <c:forEach var="row" items="${result.rows}">
                    <tr>
                        <td>${row.username}</td>
                        <td>${row.password}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>No user found with the name '${param.name}'.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>