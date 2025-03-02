<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.backend.Instruction" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Demo Show Instructions</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 600px;
            height: 350px;
            text-align: center;
            position: relative; /* Add position relative for absolute positioning of buttons */
        }

        .login-container p {
            color: #757575;
        }

        .login-container h1 {
            color: #377fa1;
            margin-bottom: 20px;
            font-size: 24px;
            font-weight: bold;
        }

        .login-container input {
            width: 90%;
            padding: 15px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .login-container button {
            padding: 5px; /* Adjust padding for smaller button */
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .login-container button:hover {
            background-color: #2a617b;
        }

        .login-container label {
            font-size: 16px;
            color: #757575;
            display: block;
            text-align: left;
            padding-left: 25px;
            margin-bottom: 5px;
        }

        .buttonsCont {
            position: absolute;
            bottom: 10px;
            left: 0;
            right: 0;
            text-align: center;
        }

        .buttonsCont button {
            margin: 0 10px;
        }

        .GoToHomepage {
            width: 200px;
            height: 40px;
            background-color: #377fa1;
            color: white;
        }
    </style>
</head>
<body>
<%
    Instruction currentInstruction = (Instruction) request.getAttribute("currentInstruction");
    boolean hasNext = currentInstruction.hasNext();
    request.setAttribute("hasNext", hasNext);
%>

<div class="login-container">

    <a class="navbar-brand navbar-dark" href="ToHomepage"> <!-- Link to the servlet -->
        <img src="resources/images/stucoLogo.png" alt="Logo" style="width:40px;" class="rounded-pill">
    </a>


    <div class="container mt-5">
        <h1><%= currentInstruction.getTitle() %></h1>
        <p><%= currentInstruction.getInformation().replace("\n", "<br>") %></p>

        <!-- Show "Go to Homepage" button only if there is no next page -->
        <c:if test="${not hasNext}">
            <form action="ToHomepage" method="get">
                <button class="GoToHomepage" type="submit">Let&#39s go!</button>
            </form>
        </c:if>

        <div class="buttonsCont">
            <!-- Go to previous instruction -->
            <form action="DemoShowInstructions" method="post" style="display: inline;">
                <input type="hidden" name="action" value="previous">
                <button type="submit" class="btn" <%= !currentInstruction.hasPrevious() ? "disabled" : "" %>>⬅️</button>
            </form>

            <!-- Go to next instruction -->
            <form action="DemoShowInstructions" method="post" style="display: inline;">
                <input type="hidden" name="action" value="next">
                <button type="submit" class="btn" <%= !currentInstruction.hasNext() ? "disabled" : "" %>>➡️</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>