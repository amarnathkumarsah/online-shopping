<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="container">
	<div class="row">
		<!-- i am adding breadcrumb here -->
		<div class="col-md-12 col-xs-12">
		
		<c:if test="${singleProduct==true}">
		
		
		<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
		<li class="breadcrumb-item"><a href="${contextRoot}/show/category/${category.id}/products">${category.name}</a></li>
		<li class="breadcrumb-item active">${product.name}</li>
		</ol>
		
		
		</c:if>
		
		</div>
		
		
	</div>
	
	<!-- Display the product Detail -->
	<div class="row">
	<!-- Display the product image -->
		<div class="col-md-4 col-xm-12">
		
			<div class="thumbnail">
			<img alt="image not available" src="${images}/${product.code}.jpg" class="img img-responsive" style="width:100%;max-width:750"/>
			
			</div>
		
		</div>
		
		<!-- Product Description -->
		<div class="col-md-8 col-xm-12">
		
		<h3>${product.name}</h3>
		<hr/>		
		
		<p>${product.description}</p>
		<hr/>
		
		<h4>Price:<strong>&#8377; ${product.unitPrice} /-</strong></h4>
		<hr/>
		
	<!-- Display the buttons -->
	<!-- if user is a Normal User -->
	<security:authorize access="hasAuthority('USER') or isAnonymous()">
		<c:choose>
		<c:when test="${product.quantity<1}">
			
			<h6>Qty. Available: <span style="color:red">out of stock!</span></h6>
		
			<a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success disabled">
				<strike>
					<i class="fa fa-cart-plus"></i>
					Add to Cart
				</strike>
			</a>
			
		</c:when>
		
		<c:otherwise>
		
		<h6>Qty. Available: ${product.quantity}</h6>
		
		<a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-lg btn-success">
		<span class="fa fa-cart-plus fa-1x text-warning"></span>
		Add to Cart</a>
		</c:otherwise>
		
		</c:choose>
		</security:authorize>
		
		<security:authorize access="hasAuthority('ADMIN')">
			
			<h6>Qty. Available: ${product.quantity}</h6>
		
			<a href="${contextRoot}/manage/${product.id}/product" class="btn btn-lg btn-warning">
			<i class="fa fa-pencil" aria-hidden="true"></i>
			Edit</a>
		
		
		
		</security:authorize>
		
		<a href="${contextRoot}/show/all/products" class="btn btn-warning">Back</a>
	</div>
	</div>
</div>