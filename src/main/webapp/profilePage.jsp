<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
<style>
<%@include file="/userDashboard.css"%>
</style>
</head>
<body>
<div class="sidebar">
<h1 class="heading">Online<br> Examination<br> Portal<br></h1>
<hr>
<a href="/online_exam_portal/userDashboard?id=<c:out value="${user.userid}"/>" class="dashboard">&nbsp<b>Dashboard</b></a><br>
<a href="/online_exam_portal/userProfile?id=<c:out value="${user.userid}"/>">&nbsp<b>Profile</b></a><br>
<a href="/online_exam_portal/resultPage?id=<c:out value="${user.userid}"/>">&nbsp<b>Result</b></a><br>
<a href="/online_exam_portal/">&nbsp<b>Logout</b></a><br>
</div>
<div class="pp" style="margin-left: 23%">
<div class="profile-page">
<table class="profile-table" cellspacing="15">
<tr>
<td colspan="3" align="center"><font size="6" color="#406c7c"><b>USER INFO</b></font></td>
</tr>
<tr>
<td rowspan="4"><img src="https://i.ibb.co/5jNyvNw/user.png"></td>
<td colspan="2"><font size="5"><c:out value="${user.username}"/></font><br>
<font size="3">&nbspUser</font><br><hr>
</td>
</tr>
<tr>
<td>User ID:</td>
<td><c:out value="${user.userid}"/></td>
</tr>
<tr>
<td>E-mail:</td>
<td><c:out value="${user.u_email}"/></td>
</tr>
<tr>
<td></td>
<td>

<button class="search-button"onclick="showpopup()">Change Password </button>
</td>
</tr>
</table>
<p align="center"><font color="red"><c:out value="${error}"/></font><font color="green"><c:out value="${success}"/></font></p>
</div>
<div class="overlay" id="overlay" style="display:none;" onclick="closepopup()"></div>
<div class="pass-popup" id="popup"> 
<div class="content">
<div class="close" onclick="closepopup()">
<button>x</button>
</div>
<form action="/online_exam_portal/profilePage" method="post">
<input type="hidden" name="id" value="<c:out value="${user.userid}"/>">
<table class="pass-table"  cellspacing="20">
<tr>
<td>Current Password</td>
<td><input type="password" name="opass" ></td>
</tr>
<tr>
<td>New Password</td>
<td><input type="password" name="npass"></td>
</tr>
<tr>
<td>Confirm Password</td>
<td><input type="password" name="cpass"></td>
</tr>
</table>
<input class="save-changes" type="submit" value="Save Changes">
</form>
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