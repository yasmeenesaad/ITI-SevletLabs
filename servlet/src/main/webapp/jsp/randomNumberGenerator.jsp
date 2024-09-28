<%@ page import="util.RandomNumberGenerator" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Random Number Generator</title>
    <style>
        .result {
            margin-top: 20px;
            padding: 10px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
    <h1>Random Number Generator</h1>

    <form method="post">
        Enter range: <input type="number" name="range">
        <input type="submit" value="Generate">
    </form>

    <div class="result">
        <%
            if (request.getParameter("range") != null) {
                int range = Integer.parseInt(request.getParameter("range"));
                int randomNumber = RandomNumberGenerator.generateRandomNumber(1, range);
                out.println("Random Number: " + randomNumber);
            }
        %>
    </div>
</body>
</html>