<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head><title>Change Password</title></head>
<body>
    <h2>Change Password</h2>
    <s:form action="updatePassword">
        <s:password name="currentPassword" label="Current Password" />
        <s:password name="newPassword" label="New Password" />
        <s:password name="confirmPassword" label="Confirm Password" />
        <s:hidden name="id" value="%{id}" />
        <s:submit value="Change Password" />
    </s:form>
    <p style="color: red;"><s:property value="message" /></p>
</body>
</html>
