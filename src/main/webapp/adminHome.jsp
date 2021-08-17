<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin </title>
<style>
<%@include file="/userDashboard.css"%>
.admindash-table{
margin-top:10%;
margin-left:30%;
}
td{
border:3px solid #406c7c;
 border-radius:5px;
 text-align:center;
 height:155px;
 padding-top:10px;
}
a{
text-decoration:none;
color:#000;
}
td:hover{
background-color:#e1e1e1;
font-weight:bold;
}

</style>
</head>

<body>
<div class="sidebar">
<h1 class="heading">Online<br> Examination<br> Portal<br></h1>
<hr>
</div>
<p class="user-name" align="right">
Hi! <c:out value="${user.username}"/>
</p>
<table class="admindash-table" cellspacing="30">
<tr>
<td><a href="/online_exam_portal/manageTests?id=<c:out value="${user.userid}"/>"><img src="https://i.ibb.co/SmJ6qtk/managetest.png"><br>Manage Tests</a></td>
<td><a href="/online_exam_portal/manageUser?id=<c:out value="${user.userid}"/>"><img src="https://i.ibb.co/99P1MVS/manageuser.png"><br>Manage Users</a></td>
<td><a href="/online_exam_portal/adminResult?id=<c:out value="${user.userid}"/>"><img src="https://i.ibb.co/JrDmgtC/viewresult.png"><br>View Results</a></td>
</tr>
</table>
</body>
</html>