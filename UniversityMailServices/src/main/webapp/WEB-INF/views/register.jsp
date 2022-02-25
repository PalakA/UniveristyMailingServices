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
					<div class="card-header">Register</div>
					<div class="card-body">
						<form name="my-form" method="post" modelAttribute="user">
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
								<label for="phone_number"
									class="col-md-4 col-form-label text-md-right">Phone
									Number</label>
								<div class="col-md-6">
									<input type="number" id="phonenumber" class="form-control"
										name="phonenumber" required>
								</div>
							</div>
							<div class="form-group row">
								<label for="nid_number"
									class="col-md-4 col-form-label text-md-right"><abbr
									title="Northeastern ID">NEU ID</abbr></label>
								<div class="col-md-6">
									<input type="text" id="neuid" class="form-control" name="neuid"
										required>
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