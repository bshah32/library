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
							<li  class="active"><a
								href='${pageContext.request.contextPath}/'>Home</a></li>
							<li><a href='${pageContext.request.contextPath}/book/bookavailability'>Book
									Availability</a></li>
							<li><a href='${pageContext.request.contextPath}/book/bookcheckin'>Book
									Checkin</a></li>
							<li><a href='${pageContext.request.contextPath}/book/bookcheckout'>Book
									Checkout</a></li>
							<li><a href='${pageContext.request.contextPath}/borrower/addborrower'>Add
									Borrower</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- /.navbar -->
		</div>
		<h2>
			<c:out value="${output}"></c:out>
		</h2>
		<hr>
		<!-- Example row of columns -->
		<div class="row-fluid">
			<div class="span12">
				<p>
				<b>1) Determining Book Availability</b> <br /> 
					Using your GUI, be able to search for a book, given any combination of book_id, title, and/or
					author_name. Your query should support substring matching. You
					should then display: book_id branch_id Both Total number and
					Available number of book copies at each branch. Hint: For Available
					number, you must subtract the number of BOOK_LOANS (i.e. COUNT(*))
					from No_of_copies for a branch. 
				</p>
				<p>
					<b>2) Book Loans Checking Out Books</b><br />					
						Using your GUI, be able to check out a book, given the combination
					of BOOK_COPIES(book_id, branch_id) and BORROWER(Card_no), i.e.
					create a new tuple in BOOK_LOANS. The date_out should be today's
					date. The due_date should be 14 days after the date_out. Each
					BORROWER is permitted a maximum of 3 BOOK_LOANS. If a BORROWER
					already has 3 BOOK_LOANS, then the checkout (i.e. create new
					BOOK_LOANS tuple) should fail and return a useful error message. If
					the number of BOOK_LOANS for a given book at a branch already
					equals the No_of_copies (i.e. There are no more book copies
					available at your library_branch), then the checkout should fail
					and return a useful error message. Checking In Books Using a GUI,
					be able to check in a book. Be able to locate BOOK_LOANS tuples by
					searching on any of book_id, Card_no, and/or any part of BORROWER
					name. Once located, provide a way of selecting one of potentially
					multiple results and a button (or menu item) to check in (i.e.
					delete that BOOK_LOANS tuple). 
					</p>
					<p>
					<b>3) Borrowers</b> <br /> 
						Using your GUI, be able to create new borrowers in the system. All name and address
					attributes are required to create a new account (i.e. not null).
					Borrowers are allowed to possess exactly one library card. If a new
					borrower is attempted withe same fname, lname, and address, then
					your system should reject and return a useful error message.</p>
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