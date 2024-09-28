<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Input Name</title>
</head>
<body>
    <h2>Enter Your Name</h2>
    <form action="search.jsp" method="post">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div>
            <input type="submit" value="Submit">
        </div>
    </form>
</body>
</html>