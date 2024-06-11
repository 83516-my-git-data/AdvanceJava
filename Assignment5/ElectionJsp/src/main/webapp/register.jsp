<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body style="text-align: center;">
		<jsp:useBean id="rb" class="com.sunbeam.beans.RegistrationBean" scope="page"/>
		
		<jsp:setProperty property="firstName" name="rb" param="firstName"/>
		<jsp:setProperty property="lastName" name="rb" param="lastName"/>
		<jsp:setProperty property="email" name="rb" param="email"/>
		<jsp:setProperty property="passwd" name="rb" param="passwd]"/>
		<jsp:setProperty property="birth" name="rb" param="birth"/>
		<jsp:setProperty property="status" name="rb" value="0"/>
		<jsp:setProperty property="role" name="rb" param="voter"/>
		<% rb.registerUser(); %>
		<% if(rb.getCount() == 1) { %>
				Registration Successful.<br/><br/>
				<a href="index.jsp">Sign In</a>
		<% }else { %>
				Registration Failed.<br/><br/>
				<a href="newuser.jsp">Sign Up</a>
		<% } %>
		
</body>
</html>