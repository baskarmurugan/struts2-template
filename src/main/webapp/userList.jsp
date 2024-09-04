<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head><title>User List</title></head>
<body>

<s:a action="logout" namespace="/">Logout</s:a>

    <h2>User List</h2>
    <s:iterator value="users">
    	<p>Id: <s:property value="id" /></p>
        <p>Username: <s:property value="username" /></p>
        <p>Email: <s:property value="email" /></p>
        <p>Password: <s:property value="password" /></p>
        <p>Role: <s:property value="role" /></p>
        <s:property value="message" />
        <s:form action="editUser">
            <s:hidden name="id" value="%{id}" />
            <s:submit value="Edit" />
        </s:form>
        <s:form action="deleteUser">
            <s:hidden name="id" value="%{id}" />
            <s:submit value="Delete" />
        </s:form>
    </s:iterator>
    
</body>
</html>
