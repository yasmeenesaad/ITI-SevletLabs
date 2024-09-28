<!-- display_data.jsp -->
<jsp:useBean id="userData" class="Beans.UserDataBean" scope="session" />
First Name: <%= userData.getFirstName() %><br>
Last Name: <%= userData.getLastName() %><br>