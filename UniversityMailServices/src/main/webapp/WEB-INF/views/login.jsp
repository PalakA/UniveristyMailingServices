<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>NEU Mail Service Login Portal</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/loginStyle.css"
	type="text/css">
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

	<div class="container">
		<form method="post" id="LoginForm" role="form" action="submitLogin">
			<div class="row mt-5">
				<div class="spContainer mx-auto">
					<div class="card px-4 py-5 border-0 shadow">
						<div class="d-inline text-left mb-3">
							<h3 class="font-weight-bold">Login</h3>
							<p>Access the Northeastern University Mail Services Portal!</p>
						</div>
						<div class="d-inline text-center mb-0">
							<div class="form-group mx-auto">
								<input class="form-control input-lg" type="text" id="emailid"
									name="emailid" placeholder="Enter Email id" required>
							</div>
						</div>
						<div class="d-inline text-center mb-3">
							<div class="form-group mx-auto">
								<input class="form-control input-lg" type="password" id="password"
									name="password" placeholder="Enter Password" autocomplete="off" required>
							</div>
						</div>
						<div class="d-inline text-left mb-3">
							<div class="form-group mx-auto">
								<button class="btn btn-primary">Confirm</button>
							</div>
						</div>
						<div class="d-inline text-left mt-3">
							<div class="form-group mx-auto mb-0">
								<a class="text-black font-weight-bold regStr"
									href="/universitymailservices/register">Register new
									account</a>
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