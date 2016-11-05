<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
		<spring:url value="/resources/script.js" var="scriptJS" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	 <link href="<c:url value="/resources/main.css" />" rel="stylesheet"/>
	<script  src="<c:url value="/resources/script.js" />"></script>
<!-- type="text/javascript"  -->
</head>
<body>
	<div id="header">
	<h1>Login Page For Splitwise</h1>
	</div>
	<h5 id = "td1">${Error}</h5>
	
	<Form action="/SplitwiseProject/welcome.html" method="post">
		<table class="table1">
			<tr>
				<td>EmailId  </td> <td> <input type= "text" name="email" /> </td>
			</tr>
			<tr>
				<td>Password </td> <td> <input type = "password" name="password"/> </td>
			</tr>
		</table>
		<br>
		<!-- <input type = "submit" value="Login"/> -->
		<button id = "button1">Login</button> 
	</Form>
	<br>
	<div1><a href="/SplitwiseProject/Signup.html">Sign Up</a></div1>
</body>
</html>