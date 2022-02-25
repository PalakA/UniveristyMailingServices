<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.mvc.pojo.packages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Northeastern Mail Services</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/mailcrmStyle.css"
	type="text/css">
<script>
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});
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
				href="/universitymailservices/facilitiesCoordinatorHome">Home
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/universitymailservices/facilitiesCoordinatorHome/createEmployees" type="button"
				title="Click to add new employess">Create Employees</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/universitymailservices/facilitiesCoordinatorHome/mailroom" type="button"
				title="Click to view mailroom system">Mail Room CRM <span
					class="sr-only">(current)</span></a></li>
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
	<div class="container-xl">
		<div class="table-responsive">
			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-sm-8">
							<h2>
								Mail Room <b>CRM</b>
							</h2>
						</div>
						<div class="col-sm-4">
							<div class="search-box">
								<i class="material-icons">&#xE8B6;</i> <input type="text"
									class="form-control" placeholder="Search&hellip;">
							</div>
						</div>
					</div>
				</div>
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th>#</th>
							<th>Package Description <i class="fa fa-sort"></i></th>
							<th>Student NEU Id<i class="fa fa-sort"></i></th>
							<th>Mail Room Number <i class="fa fa-sort"></i></th>
							<th>Aisle Number<i class="fa fa-sort"></i></th>
							<th>Clerk Name<i class="fa fa-sort"></i>
							</th>
							<th>Was Student Notified<i class="fa fa-sort"></i></th>
							<th>Package Picked Up</th>
							<th>Package Picked Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="packages" items="${packages}" varStatus="status">
							<tr>
								<td></td>
								<td>${packages.packageDesc}</td>
								<td>${packages.studentNUID}</td>
								<td>${packages.mailRoomNumber}</td>
								<td>${packages.aisleNumber}</td>
								<td>${packages.clerkName}</td>
								<%
								packages packages = (packages) pageContext.getAttribute("packages");
								if ((packages.getNotifyStudent()).equals("No")) {
								%>
								<td style="color: #FF0000">${packages.notifyStudent}</td>
								<%
								} else {
								%>
								<td style="color: #00FF00">${packages.notifyStudent}</td>
								<%
								}
								%>
								<td>${packages.packagePickedup}</td>
								<td>${packages.packagePickedupDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>