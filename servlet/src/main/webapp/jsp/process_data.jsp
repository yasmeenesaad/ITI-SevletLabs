<!-- user_input.jsp -->
<html>
<head>
<title>
thanks for registration
</title>
</head>
<body>
<jsp:useBean id="userBean" class="Beans.UserDataBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>
<h1> Thanks for Your Registration </h1>
to view your data <a href="../RegistrationServlet">Click Here </a>
</body>
</html>


