<%@page import ="util.JSPUtil"%>
<%
String name = request.getParameter("name");
out.print(JSPUtil.sayHello(name));

%>