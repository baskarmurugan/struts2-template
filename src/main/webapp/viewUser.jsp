<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head><title>User Profile</title></head>
<body>
<s:a action="logout" namespace="/">Logout</s:a>

    <h2>User Profile</h2>
    <p>Id: <s:property value="user.id" /></p>
    <p>Username: <s:property value="user.username" /></p>
    <p>Email: <s:property value="user.email" /></p>
    <s:a action="changePassword" namespace="/" style="text-decoration: none;"><button>Change password</button></s:a>    
</body>
</html>
