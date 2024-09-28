<%@ page import="Beans.UserDataBean" %>
<jsp:useBean id="userBean" class="Beans.UserDataBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<html>
<head>
    <title>Complete Your Basic Info</title>
</head>
<body>
    <form action="process_data.jsp" method="post">
        Mobile: <input type="text" name="mobile"><br>
        Email: <input type="text" name="email"><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>