<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exam</title>
<style>
<%@include file="/userDashboard.css"%>
</style>
</head>
<body>
<div style= "margin-right: 23%;padding:0px 0px px 20px; overflow:none;" >

<h1 class="page-heading" style="text-align:center">Question</h1>

<div>
<div class="question"><table> <tr> <td style="white-space:nowrap;">Q.<c:out value="${cq}"/> -<br>&nbsp</td> <td> <c:out value="${listQuestions.question}"/> </td></tr></table>
</div> <br><br>
<form class="result-dropdown" action="/online_exam_portal/examPage" method="post">
<div class="answers">
  <input type="radio" name="answer" value ="<c:out value="${listQuestions.option1}"/>">1.  <c:out value="${listQuestions.option1}"/><br>
  <input type="radio" name="answer" value ="<c:out value="${listQuestions.option2}"/>">2.  <c:out value="${listQuestions.option2}"/><br>
  <input type="radio" name="answer" value ="<c:out value="${listQuestions.option3}"/>">3.  <c:out value="${listQuestions.option3}"/><br>
  <input type="radio" name="answer" value ="<c:out value="${listQuestions.option4}"/>">4.  <c:out value="${listQuestions.option4}"/><br>
   </div>
   <br>
   <input type="hidden" name="noq" value="<c:out value="${noq}"/>">
<input type="hidden" name="cq" value="<c:out value="${cq}"/>">
<input type="hidden" name="id" value="<c:out value="${user.userid}"/>">

<input type="hidden" name="topicid" value="<c:out value="${topicid}"/>">
<input type="hidden" name="score" value="0">
  <input type="hidden" name="coption" value="<c:out value="${listQuestions.coption}"/>">
<input class="exam-button" type="submit" value="<c:out value="${button}"/> " >

 </form> 
</div>
</div>
<div class="exam-sidebar">
<p class="user-name" align="center"><font size="4.5" color="#406c7c">
Hi! <c:out value="${user.username}"/> </font>
</p>
<hr>
<p class="user-name" align="center"><font size="5" color="#406c7c">
 <c:out value="${topicname}"/></font>
</p>

<!--<table class="question-num" cellspacing="10px" align="center">

<tr><td class="done"> Q1 </td><td class="current"> Q2 </td><td> Q3 </td><td> Q4 </td></tr>
<tr><td> Q5 </td><td> Q6 </td><td> Q7 </td><td> Q7 </td></tr>
<tr><td> Q9 </td><td> Q10 </td></tr>
</table> -->
</div>


<script> 

</script>
</body>
</html>