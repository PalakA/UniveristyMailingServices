<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Northeastern Mail Services</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
				href="/universitymailservices/mailAssociateHome">Home <span
					class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/universitymailservices/mailAssociateHome/mailAssociateEditProfile" type="button"
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
	<center>
		<h2>
			<b>Mail Associate Home Page</b>
		</h2>
	</center>
	<br>
		<div class="container">
		<form method="post" id="LoginForm" role="form" action="mailAssociateHome">
			<div class="row mt-5">
				<div class="spContainer mx-auto">
					<div class="card px-4 py-5 border-0 shadow">
						<div class="d-inline text-left mb-3">
							<h3 class="font-weight-bold">Find Packages</h3>
						</div>
						<div class="d-inline text-center mb-0">
							<div class="form-group mx-auto">
								<input class="form-control input-lg" type="text" id="otp"
									name="otp" placeholder="Enter OTP" required>
							</div>
						</div>
						<div class="d-inline text-left mb-3">
							<div class="form-group mx-auto">
								<button class="btn btn-primary">View Ready to Deliver Packages</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<br>
	<%@ include file="footer.jsp"%>
</body>
</html>