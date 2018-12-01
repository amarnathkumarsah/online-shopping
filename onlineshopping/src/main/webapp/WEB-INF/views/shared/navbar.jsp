<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${contextRoot}/home">Online Shopping</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
		
			<ul class="navbar-nav ml-auto">
				<li class="nav-item" id="home"><a class="nav-link"
					href="${contextRoot}/home">Home <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item" id="about">
				<a class="nav-link" href="${contextRoot}/about">About</a>
				</li>

				<li class="nav-item" id="contact">
				<a class="nav-link" href="${contextRoot}/contact">Contact</a>
				</li>
					
				<li class="nav-item" id="listProducts">
				<a class="nav-link" href="${contextRoot}/show/all/products">View Products</a>
				</li>
				
				<security:authorize access="hasAuthority('ADMIN')">
				<li class="nav-item" id="manageProducts">
				<a class="nav-link" href="${contextRoot}/manage/products">Manage Products</a>
				</li>
				</security:authorize>
				
			</ul>
			
			<!-- Right side -->
			<ul class="nav navbar-nav navbar-right">
			
			<security:authorize access="isAnonymous()">
				
				<li class="nav-item" id="register">
					<a class="nav-link" href="${contextRoot}/register">Sign Up</a>
				</li>
			
				<li class="nav-item" id="login">
					<a class="nav-link" href="${contextRoot}/login">Login</a>
				</li>
				
			 </security:authorize>
				
				<security:authorize access="isAuthenticated()">
				 <!-- Dropdown -->
			    <li class="nav-item dropdown" id="userCart">
			    	
					<a class="nav-link dropdown-toggle" href="javascript:void(0)"  id="dropdownMenu1" data-toggle="dropdown">
						${userModel.fullName}
				  		<span class="caret"></span>
					</a>
					
				    <div class="dropdown-menu">
				    
				    <security:authorize access="hasAuthority('USER')">
				      <a class="dropdown-item" href="${contextRoot}/cart/show">
						<i class="fa fa-shopping-cart" aria-hidden="true"></i>	
						<span class="badge badge-pill badge-primary">${userModel.cart.cartLines}</span>
								- &#8377; ${userModel.cart.grandTotal}			
					  </a>
					  
				      <a class="dropdown-item" href="#">Link 2</a>
				      <a class="dropdown-item" href="#">Link 3</a>
				      <div class="dropdown-divider"></div>
				      </security:authorize>
				      <a class="dropdown-item" href="${contextRoot}/perform-logout">Logout</a>
				    </div>
				 </li>
				 
				</security:authorize>		
				
			</ul>
			
		</div>
	</div>
</nav>
<script>
window.userRole = '${userModel.role}';
</script>
