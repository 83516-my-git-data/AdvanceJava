<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
	<jsp:useBean id="rb"  class="jspapp.RegisterBean"/>
	<jsp:setProperty name="rb" property="firstName" param="firstName"/>
	<jsp:setProperty name="rb" property="lastName" param="lastName"/>
	<jsp:setProperty name="rb" property="email" param="email"/>
	<jsp:setProperty name="rb" property="password" param="passwd"/>
	<jsp:setProperty name="rb" property="birth" param="birth"/>
	<jsp:setProperty name="rb" property="status" param="status"/>
	<jsp:setProperty name="rb" property="role" param="voter"/>
	<% rb.registerUser(); %>
	<% if(rb.getCount() == 1) { %>
		Registration Successful.
		<a href="demo6.jsp">Sign In</a>
	<% } else { %>
		Login Failed.
		<a href="newuser.jsp">Sign Up</a>
	<% } %>
	
</body>
</html>