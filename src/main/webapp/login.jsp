<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<title>Login</title>
</head>

<body>
<s:form action="login">
<s:textfield name="username" label="Username" />
<s:password name="password" label="Password" />
<s:submit value="Login" />
</s:form>
<p style="color: red;"><s:property value="message" /></p>
<p>If you are a new user </p>
<s:a action="registerUser" namespace="/">Register</s:a>
</body>
</html>