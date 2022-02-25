<!DOCTYPE html>
<html lang="en">
<head>
<title>Main Header</title>
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/headerStyle.css"
	type="text/css">
</head>
<body>

	<nav class="navbar navbar-dark bg-dark navbar-dark">
		<!-- Brand/logo -->
		<a class="navbar-brand" href="/universitymailservices/home"> <img
			src="<%=request.getContextPath()%>/resources/img/logo.jpg" width="50"
			height="50" alt="NEU logo" loading="lazy"> Northeastern
			University Mail Services
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Links -->
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/universitymailservices/home">Home
						<span class="sr-only"></span>
				</a></li>
				<li class="nav-item"><a class="nav-link" target="_blank"
					href="https://www.northeastern.edu"
					title="Northeastern University - A University Like No Other"
					aria-label="Northeastern University - A University Like No Other">Explore
						Northeastern</a></li>
				<li class="nav-item"><a class="nav-link" href="/universitymailservices/login">Login to
						Portal</a></li>
			</ul>
		</div>
	</nav>

</body>
</html>