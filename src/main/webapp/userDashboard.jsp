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
<h1 class="page-heading" style="text-align:center" >Instructions</h1>
<ul>
<li>Make sure your device (mobile/tab / laptop / desktop) is ready and at your disposal for appearing for the online exam.</li>
<li>Make sure you have a good and stable Internet connection.</li>
<li>Make sure you DO NOT open any other link/tab on your browser apart from the exam link during the online exam.</li>
<li>Do not close the browser during the test before your exam is complete.</li>
<li>Do not click the 'BACK' button of browser during exam.</li>
<li>Make sure you appear for the online exam sitting alone in a well lit room with no background noise during the entire process of the exam.</li>
<li>For subjects that requires rough work, make sure you do the rough works on blank sheet and not on any notebook.</li>
<li>If any student is found to use unfair means that student's exam will be disqualified. </li>
<li>The examination will contain Multiple Choice Questions.</li>
<li>You will be rewarded 1 mark for each correct answer. No negative marking will be there.</li>
<li>After answering the question click on 'Next' button for attempting the next question. Once you reach the last question click on 'Finish' button to submit your answers.</li>
<li>You cannot re-visit the question once submitted (clicked on 'Next').   </li>
<li>Once submitted, a message shall be displayed "Your Exam has been submitted successfully" and you can logout from the student online portal.</li>
</ul>

<form class="dropdown" action="/online_exam_portal/examPage" class="signin-form" method="post">
<input type="hidden" name="id" value="<c:out value="${user.userid}"/>">
<b>Topic:</b>
  <select name="topicid" class="drpdwnopt">
  
  <option disabled selected>Select</option>
  <c:forEach var="test" items="${listTest}">
  <option value ="<c:out value="${test.topicid}"/>"><c:out value="${test.topic}"/></option>
  
  </c:forEach>
  </select>
  
<div class="start-button" style="text-align:center">
<input type="hidden" name="cq" value="0">

<input type="hidden" name="score" value="0">
<input class="Submit" type="submit" value="Start">
</div>
</form>

</body>
</html>