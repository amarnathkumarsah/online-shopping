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
<link href="${css}/bootstrap.css" rel="stylesheet">

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

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
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"  role="navigation">
		 
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="${contextRoot}/">Home</a>
			</div>
		</div>
	
	</nav>

		<!-- Page Content -->
		<div class="content">
			
	<div class="container py-5">
    <div class="row">
        <div class="col-md-12">
            <!-- <h2 class="text-center mb-4">Bootstrap 4 Login Form</h2> -->
         <c:if test="${not empty message}">
         
            <div class="row">
        	<div class="col-md-6 mx-auto">
        	
        	<div class="alert alert-danger">${message}</div>
        	
        	
        	</div>
           
           </div>
           
         </c:if>
         
             <c:if test="${not empty logout}">
         
            <div class="row">
        	<div class="col-md-6 mx-auto">
        	
        	<div class="alert alert-danger">${logout}</div>
        	
        	
        	</div>
           
           </div>
         </c:if>
         
            <div class="row">
            
                <div class="col-md-6 mx-auto">

                    <!-- form card login -->
                    <div class="card rounded-0 border-primary">
                        <div class="card-header bg-primary">
                            <h3 class="mb-0 text-white">Login</h3>
                        </div>
                        <div class="card-body">
                        <c:url value="/login" var="loginUrl"/>
                            <form class="form"  action="${loginUrl}" id="loginForm"  method="POST">
                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <input type="text" class="form-control form-control-lg rounded-0" name="username" id="username">
                                    <div class="invalid-feedback">Oops, you missed this one.</div>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control form-control-lg rounded-0" id="password" name=password autocomplete="username">
                                    <div class="invalid-feedback">Enter your password too!</div>
                                </div>
                                <div>
                                    <label class="custom-control custom-checkbox">
                                      <input type="checkbox" class="custom-control-input bg-primary" >
                                      <span class="custom-control-indicator"></span>
                                      <span class="custom-control-description small text-dark">Remember me on this computer</span>
                                    </label>
                                </div>
                               <input type="hidden"   name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-success btn-lg float-right" id="btnLogin">Login</button>
                            </form>
                        </div>
                        <!--/card-block-->
                    </div>
                    <!-- /form card login -->

                </div>


            </div>
            <!--/row-->

        </div>
        <!--/col-->
    </div>
    <!--/row-->
</div>
<!--/container-->
			
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
		
		<script src="${js}/myapp.js"></script>
	
	</div>
</body>

</html>

