<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Test</title>
<style>
<%@include file="/userDashboard.css"%>
td{
text-align:right;}</style>
</head>
<body>
<div class="sidebar">
<h1 class="heading">Online<br> Examination<br> Portal<br></h1>
<hr>
<a href="/online_exam_portal/adminHome?id=<c:out value="${user.userid}"/>" class="dashboard">&nbsp<b>Dashboard</b></a><br>
<a href="/online_exam_portal/manageUser?id=<c:out value="${user.userid}"/>">&nbsp<b>Manage Users</b></a><br>
<a href="/online_exam_portal/manageTests?id=<c:out value="${user.userid}"/>">&nbsp<b>Manage Tests</b></a><br>
<a href="/online_exam_portal/adminResult?id=<c:out value="${user.userid}"/>">&nbsp<b>View Result</b></a><br>
<a href="/online_exam_portal/adminProfile?id=<c:out value="${user.userid}"/>">&nbsp<b>Profile</b></a><br>
<a href="/online_exam_portal/">&nbsp<b>Logout</b></a><br>
</div>
<div style= "margin-left: 23%;margin-right:2%" >
<p class="user-name" align="right">
Hi!  <c:out value="${user.username}"/> 
</p>
<h1 class="page-heading" style="text-align:center">Create Test</h1>
<div align="center">

<form class="result-dropdown" action="/online_exam_portal/createTest" method="post">
<div class="question"><table> <tr> <td >Question <c:out value ="${cq}"/></td> <td><textarea name="que" rows="3" cols="90"></textarea></td></tr>


<tr><td>Options :</td></tr>
  <tr><td>1.</td><td><textarea name="opt1" rows="2" cols="90"></textarea></td></tr>
  <tr><td>2.</td><td><textarea name="opt2" rows="2" cols="90"></textarea></td></tr>
  <tr><td>3.</td><td><textarea name="opt3" rows="2" cols="90"></textarea></td></tr>
  <tr><td>4.<br>&nbsp</td><td><textarea name="opt4" rows="2" cols="90"></textarea><br>&nbsp</td></tr>
  <tr><td>
  Answer :</td>
  <td><textarea name="ans" rows="2" cols="90"></textarea></td></tr>
<input type="hidden" name ="ntid" value="<c:out value="${ntid}"/>" >
<input type="hidden" name ="noq" value="<c:out value="${noq}"/>" >
<input type="hidden" name ="cq" value="<c:out value="${cq}"/>" >
<input type="hidden" name ="id" value="<c:out value="${user.userid}"/>" >
</table>
  <br>
<input class="exam-button" type="submit" value="<c:out value="${button}"/>" >
<font color="#03fc62" size="5"><b><c:out value="${status}"/></b></font>
 </form> 
</div>
</div>
</body>
</html>