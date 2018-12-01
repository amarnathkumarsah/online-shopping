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
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<!-- to resolve active page problem -->
<script type="text/javascript">
	
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
	
</script>

<title>Online Shopping - ${title}</title>



<!-- Bootstrap core CSS -->
<%-- <link href="${css}/bootstrap.css" rel="stylesheet"> --%>

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

<!-- theme view css -->
<link href="${css}/theme-view.css" rel="stylesheet">


<!-- Linking DataTable bootstrap css -->
<link href="${css}/dataTables.bootstrap4.min.css" rel="stylesheet">

<!-- popupStyle styles for this template -->
<%-- <link href="${css}/popupStyle.css" rel="stylesheet"> --%>


<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<div class="content">
			<!-- Page Content -->
			<c:if test="${userClickHome==true}">
				<%@include file="./home.jsp"%>
			</c:if>

			<c:if test="${userClickAbout==true}">
				<%@include file="./about.jsp"%>
			</c:if>

			<!-- load only when user click contact -->
			<c:if test="${userClickContact==true}">
				<%@include file="./contact.jsp"%>
			</c:if>
			
			<!-- load only when user click all products or  category products -->
			<c:if test="${userClickAllProducts==true or userClickCategoryProducts==true}">
				<%@include file="./listProducts.jsp"%>
			</c:if>
			
			<!-- load only if singleProduct clicked by user -->
			<c:if test="${singleProduct==true}">
				<%@include file="./singleProduct.jsp"%>
			</c:if>
			
			<!-- load only if user click manage product -->
			<c:if test="${userClickManageProducts==true}">
				<%@include file="./manageProducts.jsp"%>
			</c:if>
			
			<!-- load only if user click show cart -->
			<c:if test="${userClickShowCart==true}">
				<%@include file="./cart.jsp"%>
			</c:if>
			
			
		</div>

		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js"></script>
 	
 		<!-- jQuery client side validator -->
		<script src="${js}/jquery.validate.js"></script>
		
		<!-- Bootstrap core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>


		<!-- Bootstrap core JavaScript -->
		<script src="${js}/bootstrap.bundle.min.js"></script>
		
		
		<!-- DataTable Plugin -->
		<script src="${js}/jquery.dataTables.js"></script>
		
		<script src="${js}/dataTables.bootstrap4.min.js"></script>
		
		<script src="${js}/bootbox.min.js"></script>
		
		<%-- <script src="${js}/popupJs.js"></script> --%>
		
		<script src="${js}/myapp.js"></script>
		
	</div>
</body>

</html>
