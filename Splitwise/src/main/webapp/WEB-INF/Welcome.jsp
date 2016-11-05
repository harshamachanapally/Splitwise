<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Page</title>
<link rel="stylesheet" href="<c:url value="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-route.js"></script> -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.3.1/angular-ui-router.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/smoothness/jquery-ui.css"></script>
<script type="text/javascript" src="//cdn.rawgit.com/auth0/angular-storage/master/dist/angular-storage.js"></script>
<script type="text/javascript" src= "http://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-resource.min.js"></script>
<script  src="<c:url value="/resources/script.js" />"></script>
<script  src="<c:url value="/resources/angular.js" />"></script>
<%-- <script  src="<c:url value="/resources/services/resource.js" />"></script> --%>
<link href="<c:url value="/resources/main.css" />" rel="stylesheet"/>

</head>
<body ng-controller = "myController">
<div ng-model="click(${customerId})"></div>
<div ></div>
<table class="table1">
<thead>
<tr>
	<th style="color:green;">You are Owed</th>
	<th style="color:red;">You owe</th>
	<th style="color:blue;">Net Balance</th>
</tr>
</thead>
<tbody >
	<tr>
	<td style="color:green;">{{balances[0]}}$</td>
	<td style="color:red;">{{balances[1]}}$</td>
	<td style="color:blue;">{{balances[2]}}$</td>
	</tr>
</tbody>
</table>
<br>
<table>

	<tr>
	<td class="leftbody">
	<a ui-sref="balances" ui-sref-active="activeState">Balances</a>
	<a ui-sref="friends" ui-sref-active="activeState">Friends</a>
	 <a ui-sref="trasactions" ui-sref-active="activeState">Trasactions</a>
	<a ui-sref="addtransaction" ui-sref-active="activeState">AddTransaction</a>
	</td>
	<td class="rightbody">
	<ui-view></ui-view>
	</td>
	</tr>
	<tr>
	<td colspan = "2" class="footer">
			Footer</td>
	</tr>
	

</table>

</body>



</html>