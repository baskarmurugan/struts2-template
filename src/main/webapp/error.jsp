<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            color: #721c24;
            margin: 20px;
            padding: 20px;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
        }
        h1 {
            color: #721c24;
        }
    </style>
</head>
<body>
    <h1>Error</h1>
    <p>An error occurred while processing your request.</p>
    <p><strong>Details:</strong> ${message}</p>
    <a href="login.jsp">Return to Login</a>
</body>
</html>