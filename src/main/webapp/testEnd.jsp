<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<style>
<%@include file="/userDashboard.css"%>
</style>
</head>
<body>
<div class="sidebar">
<h1 class="heading">Online<br> Examination<br> Portal<br></h1>
<hr>
<a href="/online_exam_portal/userDashboard?id=<c:out value="${user.userid}"/>" class="dashboard">&nbsp<b>Dashboard</b></a><br>
<a href="/online_exam_portal/profilePage?id=<c:out value="${user.userid}"/>">&nbsp<b>Profile</b></a><br>
<a href="/online_exam_portal/resultPage?id=<c:out value="${user.userid}"/>">&nbsp<b>Result</b></a><br>
<a href="/online_exam_portal/">&nbsp<b>Logout</b></a><br>
</div>
<div style= "margin-left: 23%" >
<p class="user-name" align="right">
Hi! <c:out value="${user.username}"/>
</p>
<div align="center">
<h1 class="page-heading" style="text-align:center" >Result</h1>
<font size="8">You Scored -  <c:out value="${score}"/>/<c:out value="${noq}"/> </font><br><br>

<a href="/online_exam_portal/userDashboard?id=<c:out value="${user.userid}"/>">Click here to go Home</a></div>
</div>
</body>
</html>