<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body style="text-align: center;">
		<jsp:useBean id="lb" class="com.sunbeam.beans.LoginBean" scope="session"/>
		<jsp:setProperty name="lb" property="*"/>
		<% lb.authenticate(); %>
		<% if(lb.getUser() != null) { %>
				<% if(lb.getUser().getRole().equals("voter")) { %>
						<% response.sendRedirect("candlist.jsp"); %>
				<% }else { %>
						<jsp:forward page="result.jsp"/>
				<% } %>
		<% }else { %>
				<h3>Online Voting</h3>
				Hello, <jsp:getProperty property="email" name="lb"/> <br/>
				Invalid Email or Password. <br/><br/>
				<a href="index.jsp">Login Again</a>
		<% } %>
</body>
</html>