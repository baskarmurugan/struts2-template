<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head><title>User Form</title></head>
<body>
<s:a action="logout" namespace="/">Logout</s:a>

    <s:form action="updateUser" method="post">
        <input type="hidden" name="id" value="${id}"/>
        <s:textfield name="username" label="Username" />
        <s:textfield name="email" label="Email" />
        <s:textfield name="password" label="Password" />
        <s:select label="Role" 
                  name="role" 
                  list="roles"  
                  headerKey="%{role}" 
                  headerValue="%{role}" />
        <s:submit value="Save" />
    </s:form>
    <p style="color: red;"><s:property value="message" /></p>
    <s:actionerror />
</body>
</html>
