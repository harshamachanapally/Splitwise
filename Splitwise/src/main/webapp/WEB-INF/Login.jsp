<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app= = "myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" href="<c:url value="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-route.js"></script> -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.3.1/angular-ui-router.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/smoothness/jquery-ui.css"></script>
<script type="text/javascript" src="//cdn.rawgit.com/auth0/angular-storage/master/dist/angular-storage.js"></script>
<script  src="<c:url value="/resources/script.js" />"></script>
<script  src="<c:url value="/resources/angular.js" />"></script>
<link href="<c:url value="/resources/main.css" />" rel="stylesheet"/>
<!-- type="text/javascript"  -->
</head>
<body ng-controller = "loginController">
	<div id="header">
	<h1>Login Page For Splitwise</h1>
	</div>
	<h5 id = "td1">${Error}</h5>
	
	<Form>
		<table class="table1">
			<tr>
				<td>EmailId  </td> <td> <input type= "text" ng-model="email" /> </td>
			</tr>
			<tr>
				<td>Password </td> <td> <input type = "password" ng-model="password"/> </td>
			</tr>
		</table>
		<br>
		<!-- <input type = "submit" value="Login"/> -->
		<button id = "button1" ng-click = "submit()">Login</button> 
	</Form>
	<br>
	<div1><a href="/SplitwiseProject/Signup.html">Sign Up</a></div1>
</body>
</html>