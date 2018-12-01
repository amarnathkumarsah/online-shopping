<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
<!--  justify-content-md-center -->
	<div class="row" >

		<c:if test="${not empty message}">
			<div class="col-md-2"></div>
			<div class="col-md-8">
			
			<div class="alert alert-${alertType} alert-dismissible">
			
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				${message}
			</div>
			</div>
			<div class="col-md-2"></div>
		</c:if>

	<div class="col-md-2"></div>
	<div class="col-md-8">
		<div class="card border-primary">
		
		<div class="card-header bg-primary " style="color: white;">Product Management</div>

		<div class="card-body">
		
			<!-- Spring form -->
			
			<sf:form class="form-horizontal" 
			modelAttribute="product" 
			action="${contextRoot}/manage/products" 
			method="POST"
			enctype="multipart/form-data"
			>
			
			<!-- Enter Product Name -->
				<div class="form-group">
				
					<div class="row">
						<label class="col-md-4 col-form-label" for="name">Enter Product Name: </label>
						
						<div class="col-md-8">
				
						<sf:input type="text" path="name" id="name" placeholder="Product Name" class="form-control"/>
						
						<sf:errors path="name" cssClass="help-block" element="em"/>
						
						</div>
						
					</div>
				
				</div>
				
				<!-- Enter Brand Name   offset-md-1  col-md-offset-1 -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="brand">Enter Brand Name: </label>
				
					<div class="col-md-8" >
				
						<sf:input type="text" path="brand" id="brand" placeholder="Brand Name" class="form-control"/>
						<sf:errors path="brand" cssClass="help-block" element="em" />
					</div>
				</div>
				</div>
				
				<!-- Enter Product Description Name   offset-md-1  col-md-offset-1 -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="description">Product Description: </label>
				
					<div class="col-md-8" >
						
						<sf:textarea rows="4"  path="description" id="description" placeholder="Write a description" class="form-control"/>
						<sf:errors path="description" cssClass="help-block" element="em" />
					</div>
				</div>
				</div>
				
				<!-- Enter unitPrice  offset-md-1  col-md-offset-1 -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="unitPrice">Enter Unit Price: </label>
				
					<div class="col-md-8" >
				
						<sf:input type="number" path="unitPrice" id="unitPrice" placeholder="Unit Price In Rs" class="form-control"/>
						<sf:errors path="unitPrice" cssClass="help-block" element="em" />
					
					</div>
				</div>
				</div>
				
				<!-- Enter Quantity Available  offset-md-1  col-md-offset-1 -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="quantity">Quantity Available: </label>
				
					<div class="col-md-8" >
				
						<sf:input type="number" path="quantity" id="quantity" placeholder="Quantity Available" class="form-control"/>
					
					</div>
				</div>
				</div>
				
				
				<!--Upload Image -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="file">Select an Image: </label>
				
					<div class="col-md-8" >
				
						<sf:input type="file" path="file" id="file" class="form-control"/>
						<sf:errors path="file" cssClass="help-block" element="em"/>
					</div>
				</div>
				</div>
				
				
				<!-- Select Category -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="category">Select Category:</label>
				
					<div class="col-md-8"  >
				
					<sf:select class="form-control" id="categoryId" path="categoryId"
					items="${categories}"
					itemLabel="name"
					itemValue="id"
					/>
					
					<c:if test="${product.id==0}">
					<br/>
					<div class="text-right">
					
						<button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#myCategoryModal">Add Category</button>
					</div>
					</c:if>
					
					</div>
				</div>
				</div>
				
				<!-- button -->
				<div class="form-group">
				<div class="row">
					<div class="col-md-auto offset-md-4">
				
						<input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary"/>
						
					</div>
				</div>
				</div>

		<!--Hidden fields  -->
	
			<sf:hidden path="id"/>
			<sf:hidden path="code"/>
			<sf:hidden path="supplierId"/>
			<sf:hidden path="active"/>
			<sf:hidden path="purchases"/>
			<sf:hidden path="views"/>
			
			</sf:form>
			
			</div>
		</div>
	</div>
	
	<div class="col-md-2"></div>
	
	</div>
	
	<div class="row">
	
	<div class="col-xs-12 col-md-12">
	<h3>Available Products</h3>
	<hr/>
	</div>
	
	<div class="col-xs-12 col-md-12">
		<div style="overflow:auto;">
		<!-- Products table for Admin -->
		<table id="adminProductsTable" class="table table-striped table-bordered">
		 <thead>
		  <tr>
		    <th>Id</th>
		    <th>&#160;</th>
		    <th>Name</th>
		    <th>Brand</th>
		    <th>Quantity</th>
		    <th>Unit Price</th>
		    <th>Active</th>
		    <th>Edit</th>
		  </tr>
		  </thead>
		 <%--  
		 commented because we are retrieving from dataTable plug-in
		 <tbody>
			  <tr>
			  	<td>4</td>
			  	<td>
			  	
			  	<img class="adminTableDataImg" src="${contextRoot}/resources/images/PRDMNO123PQRX.jpg" 
			  	alt="Macbook Pro"/>
			  	
			  	</td>
			  	<td>Macbook Pro</td>
			  	<td>Apple</td>
			  	<td>5</td>
			  	<td>&#8377; 55000.00/-</td>
			  	<td>
			  	<!-- toggle switch -->
			  	<label class="switch">
			  		<input type="checkbox" checked="checked" value="4" />
			  		<div class="slider round"></div>
			  	</label>
			  	</td>
			  	<td>
			  	
			  	<a href="${contexRoot}/manage/4/product" class="btn btn-warning">
			  	<i class="fa fa-pencil" aria-hidden="true"></i>
			  	</a>
			  	</td>
			  </tr>
			  
			  
			  <tr>
			  	<td>4</td>
			  	<td>
			  	
			  	<img class="adminTableDataImg" src="${contextRoot}/resources/images/PRDMNO123PQRX.jpg" 
			  	alt="Macbook Pro"/>
			  	
			  	</td>
			  	<td>Macbook Pro</td>
			  	<td>5</td>
			  	<td>&#8377; 55000.00/-</td>
			  	<td>
			  	<!-- toggle switch -->
			  	<label class="switch">
			  		<input type="checkbox" value="4" />
			  		<div class="slider round"></div>
			  	</label>
			  	
			  	</td>
			  	<td>
			  	
			  	<a href="${contextRoot}/manage/4/product" class="btn btn-warning">
			  	<i class="fa fa-pencil" aria-hidden="true"></i>
			  	</a>
			  	</td>
			  </tr>
		  </tbody> --%>
		  <tfoot>
		  
		  <tr>
		    <th>Id</th>
		    <th>&#160;</th>
		    <th>Name</th>
		    <th>Brand</th>
		    <th>Quantity</th>
		    <th>Unit Price</th>
		    <th>Active</th>
		    <th>Edit</th>
		  </tr>
		  
		  </tfoot>
		</table>

		
		</div>
	
	</div>
	
	</div>
</div>

<div class="modal fade" id="myCategoryModal" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add New Category</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <sf:form id="categoryForm" 
      		class="form-horizontal" 
			modelAttribute="category" 
			action="${contextRoot}/manage/new/category" 
			method="POST">
      <div class="modal-body">
        
          <div class="form-group">
            <label for="category_name" class="col-form-label">Category Name</label>
            <sf:input type="text" path="name" class="form-control" id="category_name" />
          </div>
          
          <div class="form-group">
            <label for="category_description" class="col-form-label">Category Description</label>
            <sf:textarea path="description" class="form-control" rows="5" id="category_description"></sf:textarea>
          </div>
          
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Add Category</button>
      </div>
      </sf:form>
    </div>
  </div>
</div>

		<!-- 		
			<h4 class="card-title">Card title</h4>
			<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
			<p class="card-text">Some quick example text to build on the card
				title and make up the bulk of the card's content.</p>
			<a href="#" class="card-link">Card link</a>
			<div class="card-footer">Footer</div>
			 -->