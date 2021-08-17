<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Users</title>
<link rel="stylesheet" href="userDashboard.css">
<style>
<%@include file="/userDashboard.css"%>
img{
height:25px;
width:20px;
}
</style>
</head>
<body>
<div class="sidebar">
<h1 class="heading">Online<br> Examination<br> Portal<br></h1>
<hr>
<a href="/online_exam_portal/adminHome?id=<c:out value="${user.userid}"/>" class="dashboard">&nbsp<b>Dashboard</b></a><br>
<a href="/online_exam_portal/manageTests?id=<c:out value="${user.userid}"/>">&nbsp<b>Manage Tests</b></a><br>
<a href="/online_exam_portal/adminResult?id=<c:out value="${user.userid}"/>">&nbsp<b>View Result</b></a><br>
<a href="/online_exam_portal/adminProfile?id=<c:out value="${user.userid}"/>">&nbsp<b>Profile</b></a><br>
<a href="/online_exam_portal/">&nbsp<b>Logout</b></a><br>
</div>
<div style= "margin-left: 23%;  margin-right:2%;" >
<p class="user-name" align="right">
Hi! <c:out value="${user.username}"/>
</p>
<h1 class="page-heading" style="text-align:center">Manage Users</h1>
<input class="exam-button" type="button" value="Add User" onclick="showpopup()"><br>
<table border="1" style="border-collapse:collapse;width:100%;text-align:center;" cellpadding="4px">
<tr>
<th>S. NO.</th><th>User id</th><th>User Name</th><th>E-mail</th><th>Delete</th>
</tr>
<c:forEach var="User" items="${listUser}">
<tr><td>1</td><td><c:out value="${User.userid}"/></td><td><c:out value="${User.username}"/></td><td><c:out value="${User.u_email}"/></td><td><a href="/online_exam_portal/manageUser?id=<c:out value="${user.userid}"/>&delid=<c:out value="${User.userid}"/>"><img src="https://i.ibb.co/HpRt3Cf/delete.png"></a></td></tr>
</c:forEach>

</table>
<div class="overlay" id="overlay" style="display:none;" onclick="closepopup()"></div>
<div class="add-popup" id="popup"> 
<div class="content">
<div class="close" onclick="closepopup()">
<button>x</button>
</div>
<h1>Subject Name</h1>
<div>
<form action="/online_exam_portal/manageUser?id=<c:out value="${user.userid}"/>" method="post">
<table class="result-table" border="1" cellpadding="18">
<tr>
<td>User Name</td>
<td><input name="username" type="text"></td>
</tr>

<tr>
<td>E-mail</td>
<td><input name="email" type="text"></td>
</tr>
<tr>
<td>Password</td>
<td><input name="password" type="password"></td>
</tr>
</table>
<input class="exam-button" type="submit" value="Add User" onclick="closepopup()">
</form>
</div>
</div>
</div>
</div>
<script> 
function showpopup(){
	document.getElementById("popup").classList.toggle("active");
	document.getElementById("overlay").style.display = "block";

}
function closepopup(){
	document.getElementById("popup").classList.toggle("active");
	document.getElementById("overlay").style.display = "none";

}
</script>
</body>
</html>