<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> Welcome to your first JSP </title>
</head>
<body>

<%@include file="header.jsp"%>

<h1>Enter your name </h1>

<form action="welcome.jsp">
    <br> Enter your name <input type="text" name="name">
    <br> <input type="submit" value="Say Hello">

</form>
<jsp:include page="footer.jsp"/>
</body>
</html>