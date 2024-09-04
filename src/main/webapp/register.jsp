<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<title>Register</title>
</head>

<body>
<s:form action="register" method="post">
<s:textfield name="username" label="Username" />
<s:fielderror fieldName="username" />

<s:password name="password" label="Password" />
<s:fielderror fieldName="password" />

<s:textfield name="email" label="Email" type="email" />
<s:fielderror fieldName="email" />
<s:select label="Role" 
              name="selectedRole"  
              list="roles" 
              headerKey="" 
              headerValue="Select Role" />
    <s:fielderror fieldName="selectedRole" />
<s:submit value="Register" />
</s:form>
<p style="color: red;"><s:property value="message" /></p>
</body>
</html>