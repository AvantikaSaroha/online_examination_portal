<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Tests</title>
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
<a href="/online_exam_portal/manageUser?id=<c:out value="${user.userid}"/>">&nbsp<b>Manage Users</b></a><br>
<a href="/online_exam_portal/adminResult?id=<c:out value="${user.userid}"/>">&nbsp<b>View Result</b></a><br>
<a href="/online_exam_portal/adminProfile?id=<c:out value="${user.userid}"/>">&nbsp<b>Profile</b></a><br>
<a href="/online_exam_portal/">&nbsp<b>Logout</b></a><br>
</div>
<div style= "margin-left: 23%; margin-right:2%;" >
<p class="user-name" align="right">
Hi! <c:out value="${user.username}"/>
</p>
<h1 class="page-heading" style="text-align:center">Manage Tests</h1><font size ="5" color="#03fc62"> <c:out value="${status}"/></font><font color="red"> <c:out value="${error}"/></font>
<input class="exam-button" type="button" value="Add Test" onclick="showpopup()"><br>
<table border="1" style="border-collapse:collapse;width:100%;text-align:center;" cellpadding="4px">
<tr>
<th>Topic id</th><th>Topic Name</th><th>Delete</th>
</tr>
<c:forEach var="Test" items="${listTest}">
<tr><td><c:out value="${Test.topicid}"/></td><td><c:out value="${Test.topic}"/></td><td><a href="/online_exam_portal/manageTests?id=<c:out value="${user.userid}"/>&delid=<c:out value="${Test.topicid}"/>"><img src="https://i.ibb.co/HpRt3Cf/delete.png"></a></td></tr>
</c:forEach>
</table>
<div class="overlay" id="overlay" style="display:none;" onclick="closepopup()"></div>
<div class="add-popup" id="popup"> 
<div class="content">
<div class="close" onclick="closepopup()">
<button>x</button>
</div>
<h1>Subject Name</h1>
<div><form action="/online_exam_portal/createTest" method="post">
<table class="result-table" border="1" cellpadding="18">
<tr>
<td>Topic Name</td>
<td><input type="text" name="ntname"></td>
</tr>
<tr>
<td>Number of questions</td>
<td><input type="text" name="noq"></td>
<input type="text" name="cq" value="0" hidden>

<input type="hidden" name="id" value="<c:out value="${user.userid}"/>" >
</tr>
</table>
<input class="exam-button" type="submit" value="Add Test" onclick="closepopup()">
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