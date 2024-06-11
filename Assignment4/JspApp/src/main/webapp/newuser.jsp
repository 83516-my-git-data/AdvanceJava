<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New User</title>
</head>
<body style="text-align: center;">
	<h2>Online Voting</h2>
	<form method="post" action="register.jsp">
		FirstName : <input type="text" name="firstName"/> <br/><br/>
		LastName  : <input type="text" name="lastName"/> <br/><br/>
		Email     : <input type="text" name="email"/> <br/><br/>
		Password  : <input type="password" name="passwd"/> <br/><br/>
		Birth    : <input type="date" name="birth"/> <br/><br/>
		<input type="submit" value="Sign Up"/> &nbsp&nbsp&nbsp&nbsp 
		<a href="demo6.jsp">Already have an Account</a>
	</form>
</body>
</html>