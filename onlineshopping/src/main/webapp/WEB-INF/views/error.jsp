<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/resources/css" var="css"></spring:url>
<spring:url value="/resources/js" var="js"></spring:url>
<spring:url value="/resources/images" var="images"></spring:url>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- to resolve active page problem -->
<script type="text/javascript">

	window.contextRoot = '${contextRoot}';
	
</script>

<title>Online Shopping - ${title}</title>



<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">


<!-- theme view css -->
<link href="${css}/theme-view.css" rel="stylesheet">


<!-- Linking DataTable bootstrap css -->
<link href="${css}/dataTables.bootstrap4.min.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">
</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Home</a>
				</div>
				
			</div>
		</nav>
		
				
		<div class="content">
			
			<div class="container">
			
				<div class="row">
				
				<div class="col-xs-12 col-md-12">
				
					<div class="jumbotron">
					
						<h1>${errorTitle}</h1>
						<hr/>
						
						<blockquote style="word-wrap:break-word">
							${errorDescription}
						</blockquote>
					</div>
				
				</div>
				
				
				</div>
			
			</div>
			
		</div>

		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>


		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js"></script>
		
		
		<script src="${js}/myapp.js"></script>
		
	</div>
</body>

</html>
