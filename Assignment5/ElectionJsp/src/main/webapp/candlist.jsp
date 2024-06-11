<%@page import="com.sunbeam.pojos.Candidate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Candidates</title>
</head>
<body style="text-align: center;">
		<h3>Online Voting</h3>
		<jsp:useBean id="lb" class="com.sunbeam.beans.LoginBean" scope="session"/>
		Hello, <jsp:getProperty property="email" name="lb"/> <hr/>
		<jsp:useBean id="clb" class="com.sunbeam.beans.CandidateListBean"/>
		<% clb.fetchCandidates(); %>
		<form method="post" action="vote.jsp">
				<% for(Candidate c : clb.getCandidateList()){ %>
						<input type="radio" name="candidate" value="<%= c.getId() %>"/> <%= c.getName() %> - <%= c.getParty() %>
				<% } %>
				<br/> <input type="submit" value="Vote" />
		</form>
		
</body>
</html>