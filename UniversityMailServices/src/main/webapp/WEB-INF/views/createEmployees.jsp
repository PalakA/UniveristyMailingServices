<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Fonts -->
<link rel="dns-prefetch" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600"
	rel="stylesheet" type="text/css">

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/registerStyle.css"
	type="text/css">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script>
	var check = function() {
		if (document.getElementById('password').value == document
				.getElementById('confirmPassword').value) {
			document.getElementById('message').style.color = 'green';
			document.getElementById('message').innerHTML = 'Matching';
		} else {
			document.getElementById('message').style.color = 'red';
			document.getElementById('message').innerHTML = 'Not Matching';
		}
	}
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> <!-- Brand/logo -->
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item active"><a class="nav-link"
				href="/universitymailservices/facilitiesCoordinatorHome">Home </a></li>
			<li class="nav-item"><a class="nav-link"
				href="/universitymailservices/admin/createEmployees" type="button"
				title="Click to add new employess">Create Employees <span
					class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a class="nav-link"
				href="/universitymailservices/facilitiesCoordinatorHome/mailroom" type="button"
				title="Click to view mailroom system">Mail Room CRM</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/universitymailservices/facilitiesCoordinatorHome/facilitiesCoordinatorEditProfile" type="button"
				title="Click to edit your profile">Edit Your Profile</a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
		<div class="nav navbar-nav navbar-right">
			<a href="/universitymailservices/userLogout" type="button"
				class="nav-link" title="Click to Logout">Logout</a>
		</div>
	</div>
	</nav>
	<c:set var="msg" value="${msg}" />
	<c:if test="${msg!=null}">
		<div class="alert alert-success" role="alert">
			<strong>${msg}</strong>
		</div>
	</c:if>
	<c:set var="errmsg" value="${errmsg}" />
	<c:if test="${errmsg!=null}">
		<div class="alert alert-danger" role="alert">
			<strong>${errmsg}</strong>
		</div>
	</c:if>
	<br>

	<br>
	<main class="my-form">
	<div class="cotainer">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header">Register New Employee</div>
					<div class="card-body">
						<form name="my-form" method="post" modelAttribute="user">
							<div class="form-group row">
								<label for="id" class="col-md-4 col-form-label text-md-right">User
									Role</label>
								<div class="col-md-6">
									<select class="form-control" name="userrole">
										<c:forEach var="userrole" items="${userrole}">
											<option>${userrole}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label for="full_name"
									class="col-md-4 col-form-label text-md-right">First
									Name</label>
								<div class="col-md-6">
									<input type="text" id="firstName" class="form-control"
										name="firstName" required>
								</div>
							</div>
							<div class="form-group row">
								<label for="last_name"
									class="col-md-4 col-form-label text-md-right">Last Name</label>
								<div class="col-md-6">
									<input type="text" id="lastName" class="form-control"
										name="lastName" required>
								</div>
							</div>

							<div class="form-group row">
								<label for="email_address"
									class="col-md-4 col-form-label text-md-right">E-Mail
									Address</label>
								<div class="col-md-6">
									<input type="text" id="email_address" class="form-control"
										name="email_address" required>
								</div>
							</div>
							<div class="form-group row">
								<label for="mailroomnumber"
									class="col-md-4 col-form-label text-md-right">Mail Room
									Number</label>
								<div class="col-md-6">
									<input type="number" id="mailroomnumber" class="form-control"
										name="mailroomnumber" required>
								</div>
							</div>
							<div class="form-group row">
								<label for="password"
									class="col-md-4 col-form-label text-md-right">Password</label>
								<div class="col-md-6">
									<input type="password" class="form-control" name="password"
										id="password" placeholder="Enter Password" htmlEscape="true"
										required onkeyup='check();' />
								</div>
							</div>

							<div class="form-group row">
								<label for="confirmPassword"
									class="col-md-4 col-form-label text-md-right">Confirm
									Password</label>
								<div class="col-md-6">
									<input type="password" class="form-control"
										name="confirmPassword" id="confirmPassword" onkeyup='check();'
										placeholder="Confirm Password" htmlEscape="true" required />
									<span id='message'></span>
								</div>
							</div>
							<div class="form-group row">
								<label for="phonenumber"
									class="col-md-4 col-form-label text-md-right">Phone
									Number</label>
								<div class="col-md-6">
									<input type="number" id="phonenumber" class="form-control"
										name="phonenumber" required>
								</div>
							</div>
							<div class="col-md-6 offset-md-4">
								<button type="submit" class="btn btn-primary">Register
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>
	<br>
	<%@ include file="footer.jsp"%>
</body>
</html>