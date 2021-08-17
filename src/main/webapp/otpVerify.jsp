<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
  <head>
  	<title>Online Exam Portal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<style><%@include file="/css/style.css"%></style>
	</head>
	<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
				&nbsp<h2 class="heading-section"></h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-5">
					<div class="wrap">
						<div class="img" style="background-image: url(https://i.ibb.co/kB53fJR/bg-1.jpg);"></div>
						<div class="login-wrap p-4 p-md-5">
			      	<div class="d-flex">
			      		<div class="w-100">
			      			<h3 class="mb-4">Reset Password</h3>
			      		</div>
			      	</div>
							<form action="/online_exam_portal/setPassword" class="signin-form" method="post">
			      		<div class="form-group mt-3">
			      			<input name="uotp" type="text" class="form-control" required>
			      			<label class="form-control-placeholder" for="username">Enter OTP</label>
			      		</div>
		            
		            <div class="form-group">
		            <input type="hidden" value ="<c:out value="${sotp}"/>" name="sotp">
		             <input type="hidden" value ="<c:out value="${email}"/>" name="email">
		            	<button type="submit" class="form-control btn btn-primary rounded submit px-3">Verify OTP</button>
		            </div>
		            <div class="form-group d-md-flex">
									<div class="w-50 text-md-right">
										<a href="/online_exam_portal/" >Login</a>
									</div>
		            </div>
		          </form>
		          </div>
		      </div>
				</div>
			</div>
		</div>
	</section>

	<script src="js/jquery.min.js"></script>
  <script src="js/popper.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/main.js"></script>

	</body>
</html>

