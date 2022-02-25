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
	href="<%=request.getContextPath()%>/resources/css/homeStyle.css"
	type="text/css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<img src="<%=request.getContextPath()%>/resources/img/mailLogo.png"
		alt="NEU mail services logo" width="100%">
	<br>
	<br>
	<center>
		<h1>
			<b>Welcome to Mail Services</b>
		</h1>
	</center>
	<br>
	<div class="intro">
		Mail and Package Services is the source for all incoming and outgoing
		mail. University Mail Services is the central mailing center for the
		University of Northeastern and functions as the liaison with the
		United States Postal Service by both delivering and collecting first
		class and bulk mailings. Mail Services uses a tracking system to scan
		in and track most packages for students. Students receive notification
		via the web portal of any packages available for pick-up. Large
		packages received via the U.S. Mail (U.S.P.S.) are scanned in with a
		notification sent to students.
		</p>
		<p>
		<h4>Shipping Services</h4>
		<br>Shipping Services Mail Services provides shipping services
		for all Northeastern faculty, staff and students! We sell postage
		stamps (singles, books, or rolls) and shipping supplies including a
		variety of boxes, packaging tape, bubble wrap and more - everything to
		meet your shipping needs. Ship your packages through us via the USPS,
		FedEx or UPS. <br> <br>
		<h4>Come visit us</h4>
		<br>
		<div class="row">
			<div class="col-9">
				<u><b>Speare Mailroom</b></u>
			</div>
			<br> <br>
			<div class="col-4">
				<b>Package Pick-up Hours</b><br> <img
					src="<%=request.getContextPath()%>/resources/img/time_img.png"
					class="img-rounded" alt="time_img" width="5%"> Monday-Friday:
				8AM - 8PM<br> <img
					src="<%=request.getContextPath()%>/resources/img/time_img.png"
					class="img-rounded" alt="time_img" width="5%"> Saturday: 10AM
				- 4PM<br> <img
					src="<%=request.getContextPath()%>/resources/img/time_img.png"
					class="img-rounded" alt="time_img" width="5%"> Sunday: CLOSED
			</div>
			<div class="col-6">
				<b>Residential Mail</b><br> <a
					href="https://www.google.com/maps/place/7+Speare+Pl,+Boston,+MA+02115/@42.3407327,-71.0924584,17z/data=!3m1!4b1!4m5!3m4!1s0x89e37a18dadcc293:0xd6c6e32ac2ef7e0e!8m2!3d42.3407288!4d-71.0902697"
					target="_blank"> <img
					src="<%=request.getContextPath()%>/resources/img/Address_img.png"
					class="img-rounded" alt="Address_img" width="5%"> 7 Speare
					Pl.
				</a><br> <img
					src="<%=request.getContextPath()%>/resources/img/contact_img.png"
					class="img-rounded" alt="contact_img" width="4%">
				617-373-2529 <br> <a
					href="mailto:ResidentialMail@northeastern.edu"> <img
					src="<%=request.getContextPath()%>/resources/img/mail_img.png"
					class="img-rounded" alt="mail_img" width="5%">
					ResidentialMail@northeastern.edu
				</a>

			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-9">
				<u><b>Columbus Mailroom</b></u>
			</div>
			<br> <br>
			<div class="col-4">
				<b>Package Pick-up Hours</b><br> <img
					src="<%=request.getContextPath()%>/resources/img/time_img.png"
					class="img-rounded" alt="time_img" width="5%"> Monday-Friday:
				8AM - 8PM<br> <img
					src="<%=request.getContextPath()%>/resources/img/time_img.png"
					class="img-rounded" alt="time_img" width="5%"> Saturday: 10AM
				- 4PM<br> <img
					src="<%=request.getContextPath()%>/resources/img/time_img.png"
					class="img-rounded" alt="time_img" width="5%"> Sunday: CLOSED
			</div>
			<div class="col-6">
				<b>Residential Mail</b><br> <a
					href="https://www.google.com/maps/place/Columbus+Place+and+Alumni+Center,+716+Columbus+Ave,+Boston,+MA+02120/@42.3376814,-71.0874785,17z/data=!3m1!4b1!4m5!3m4!1s0x89e37a3d53f63deb:0x57184da99c118711!8m2!3d42.3376775!4d-71.0852898"
					target="_blank"> <img
					src="<%=request.getContextPath()%>/resources/img/Address_img.png"
					class="img-rounded" alt="Address_img" width="5%"> 716
					Columbus Ave.
				</a><br> <img
					src="<%=request.getContextPath()%>/resources/img/contact_img.png"
					class="img-rounded" alt="contact_img" width="4%">
				617-373-5178 <br> <a
					href="mailto:ResidentialMail@northeastern.edu"> <img
					src="<%=request.getContextPath()%>/resources/img/mail_img.png"
					class="img-rounded" alt="mail_img" width="5%">
					ResidentialMail@northeastern.edu
				</a>

			</div>
		</div>

		<br>
		<h4>Mail & Package Delivery</h4>
		<br> Mail & Package Delivery Mail and/or packages are delivered
		daily by the USPS, FedEx, UPS and DHL as well as Amazon Direct. While
		your carrier may have sent you an e-mail stating your package has been
		delivered to Northeastern, Mail Services needs time to sort and
		process those packages for delivery to you. Package processing time
		may vary depending on incoming volume. However, we make every effort
		to have all packages processed and available for pick-up within 24
		hours of receipt.
		</p>
	</div>

	<br>
	<%@ include file="footer.jsp"%>
</body>
</html>
