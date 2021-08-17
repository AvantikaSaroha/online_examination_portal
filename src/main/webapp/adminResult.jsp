<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Result</title>
<style>
<%@include file="/userDashboard.css"%>
</style>
</head>
<body>
<div class="sidebar">
<h1 class="heading">Online<br> Examination<br> Portal<br></h1>
<hr>
<a href="/online_exam_portal/adminHome?id=<c:out value="${user.userid}"/>" class="dashboard">&nbsp<b>Dashboard</b></a><br>
<a href="/online_exam_portal/manageUser?id=<c:out value="${user.userid}"/>">&nbsp<b>Manage Users</b></a><br>
<a href="/online_exam_portal/manageTests?id=<c:out value="${user.userid}"/>">&nbsp<b>Manage Tests</b></a><br>
<a href="/online_exam_portal/adminProfile?id=<c:out value="${user.userid}"/>">&nbsp<b>Profile</b></a><br>
<a href="/online_exam_portal/">&nbsp<b>Logout</b></a><br>
</div>
<div style= "margin-left: 23%" >
<p class="user-name" align="right">
Hi! Avantika Saroha
</p>
<h1 class="page-heading" style="text-align:center">Result</h1>

<div style="white-space:nowrap;">

<form action="/online_exam_portal/adminResult" class="result-dropdown" method="post"><b>Topic:</b>

<input type="hidden" name="id" value="<c:out value="${user.userid}"/>">
   <select name="topicid" class="drpdwnopt">
  
  <option disabled selected>Select</option>
  <c:forEach var="test" items="${listTest}">
  <option value ="<c:out value="${test.topicid}"/>"><c:out value="${test.topic}"/></option>
  
  </c:forEach>
  </select>
  
<input class="search-button" type="submit" value="show">

 </form> 
 
</div><br><br>
<table border="1" style="border-collapse:collapse;width:100%;text-align:center;" cellpadding="4px">
<tr>
<th>User Name</th><th>Total Marks</th><th>Marks Obtained</th>
</tr>
<c:forEach var="Result" items="${listResult}">
<tr><td><c:out value="${Result.topicname}"/></td><td><c:out value="${Result.totalmarks}"/></td><td><c:out value="${Result.marksobt}"/></td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>