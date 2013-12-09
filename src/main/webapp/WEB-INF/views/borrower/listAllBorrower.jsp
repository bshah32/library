<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Library Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link rel="stylesheet" type="text/css"
	href='${pageContext.request.contextPath}/css/bootstrap.css'>
<style type="text/css">
body {
	padding-top: 20px;
	padding-bottom: 60px;
}

/* Custom container */
.container {
	margin: 0 auto;
	max-width: 1000px;
}

.container>hr {
	margin: 60px 0;
}

/* Main marketing message and sign up button */
.jumbotron {
	margin: 80px 0;
	text-align: center;
}

.jumbotron h1 {
	font-size: 100px;
	line-height: 1;
}

.jumbotron .lead {
	font-size: 24px;
	line-height: 1.25;
}

.jumbotron .btn {
	font-size: 21px;
	padding: 14px 24px;
}

/* Supporting marketing content */
.marketing {
	margin: 60px 0;
}

.marketing p+h4 {
	margin-top: 28px;
}

/* Customize the navbar links to be fill the entire space of the .navbar */
.navbar .navbar-inner {
	padding: 0;
}

.navbar .nav {
	margin: 0;
	display: table;
	width: 100%;
}

.navbar .nav li {
	display: table-cell;
	width: 1%;
	float: none;
}

.navbar .nav li a {
	font-weight: bold;
	text-align: center;
	border-left: 1px solid rgba(255, 255, 255, .75);
	border-right: 1px solid rgba(0, 0, 0, .1);
}

.navbar .nav li:first-child a {
	border-left: 0;
	border-radius: 3px 0 0 3px;
}

.navbar .nav li:last-child a {
	border-right: 0;
	border-radius: 0 3px 3px 0;
}
</style>

<link rel="stylesheet" type="text/css"
	href='${pageContext.request.contextPath}/css/bootstrap-responsive.css'>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="${pageContext.request.contextPath}/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="${pageContext.request.contextPath}/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="${pageContext.request.contextPath}/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="${pageContext.request.contextPath}/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/ico/favicon.png">
</head>

<body>

	<div class="container">
		<div class="masthead">
			<h3 class="muted">Library Project</h3>
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container">
						<ul class="nav">
							<li><a href='${pageContext.request.contextPath}/'>Home</a></li>
							<li class="dropdown"><a href="#"
								class="dropdown-toggle active" data-toggle="dropdown">Book <b
									class="caret"></b></a>
								<ul class="dropdown-menu">
								<li><a
										href='${pageContext.request.contextPath}/book/listallbook'>Book
											List</a></li>
									<li><a
										href='${pageContext.request.contextPath}/book/bookavailability'>Book
											Availability</a></li>
									<li><a
										href='${pageContext.request.contextPath}/book/bookcheckout'>Book
											Checkout</a></li>
									<li><a
										href='${pageContext.request.contextPath}/book/bookcheckin'>Book
											Checkin</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Borrower <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a
										href='${pageContext.request.contextPath}/borrower/addborrower'>Add
											Borrower</a></li>
											<li><a
								href='${pageContext.request.contextPath}/borrower/listBorrower'>List
									Borrower</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Branch <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a
										href='${pageContext.request.contextPath}/branch/insertbranch'>Add
											Branch</a></li>
									<li><a
										href='${pageContext.request.contextPath}/branch/listallbranch'>View
											All Branches</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- /.navbar -->
		</div>
		<h2>
			All Borrowers
		</h2>
		<div class="alert">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>Status : </strong>
			<c:out value="${status}"></c:out>
		</div>
		<hr>
		<!-- Example row of columns -->
		<div class="row-fluid">
			<div class="span12">
				<div class="table-responsive">
				<a href="${pageContext.request.contextPath}/borrower/borrowerlistview/openInPDF"><b>Show In PDF</b></a>
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>Card No</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Address</th>
								<th>Phone</th>
								<th>Update/Delete</th>
							</tr>
						<thead>
						<tbody>
							<c:forEach items="${borrower_model}" var="borrowerModel">
								<tr>
									<td>${borrowerModel.card_no}</td>
									<td>${borrowerModel.fname}</td>
									<td>${borrowerModel.lname}</td>
									<td>${borrowerModel.address}</td>
									<td>${borrowerModel.phone}</td>
									<td><a
										href='${pageContext.request.contextPath}/borrower/onborrowerupdate/${borrowerModel.card_no}'>
											<i class="icon-edit"></i>
									</a> &nbsp; <a
										href='${pageContext.request.contextPath}/borrower/onborrowerdelete/${borrowerModel.card_no}'>
											<i class="icon-trash"></i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<hr>
		<div class="footer">
			<p>&copy; Deep Shah</p>
		</div>

	</div>
	<!-- /container -->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript"
		src='${pageContext.request.contextPath}/js/jquery.js'></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>