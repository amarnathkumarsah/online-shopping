<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../shared/flows-header.jsp" %>
			
		<div class="container">
		<div class="row" >

	<div class="col-md-2"></div>
	<div class="col-md-8">
		<div class="card border-primary">
		
		<div class="card-header bg-primary " style="color: white;">SignUp - Personal</div>

		<div class="card-body">
		
			<!-- Spring form -->
			
			<sf:form class="form-horizontal" 
			modelAttribute="user" 
			method="POST"
			id="registerForm"
			>
			
			<!-- Enter Product Name -->
				<div class="form-group">
				
					<div class="row">
						<label class="col-md-4 col-form-label" for="firstName">First Name: </label>
						
						<div class="col-md-8">
				
							<sf:input type="text" path="firstName" id="firstName" placeholder="First Name" class="form-control"/>
							
							<sf:errors path="firstName" cssClass="help-block" element="em"/>
						
						</div>
						
					</div>
					
				</div>
				
				<!-- Enter Brand Name   offset-md-1  col-md-offset-1 -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="lastName">Last Name: </label>
				
					<div class="col-md-8" >
				
						<sf:input type="text" path="lastName" id="lastName" placeholder="Last Name" class="form-control"/>
						<sf:errors path="lastName" cssClass="help-block" element="em" />
					</div>
				</div>
				</div>
				
				<!-- Enter Email   offset-md-1  col-md-offset-1 -->
				<div class="form-group">
					<div class="row">
						<label class="col-md-4 col-form-label" for="email">Email: </label>
					
						<div class="col-md-8" >
					
							<sf:input type="text" path="email" id="email" placeholder="abc@xyz.com" class="form-control"/>
							<sf:errors path="email" cssClass="help-block" element="em" />
							
						</div>
					</div>
				</div>
				
				<!-- Enter unitPrice  offset-md-1  col-md-offset-1 -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="contactNumber">Contact Number: </label>
				
					<div class="col-md-8" >
				
						<sf:input type="number" path="contactNumber" id="contactNumber" placeholder="Contact Number" class="form-control"/>
						<sf:errors path="contactNumber" cssClass="help-block" element="em" />
					
					</div>
				</div>
				</div>
				
				<!-- Enter Password  offset-md-1  col-md-offset-1 -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="password">Password: </label>
				
					<div class="col-md-8" >
				
						<sf:password path="password" id="password" placeholder="Password" class="form-control"/>
						<sf:errors path="password" cssClass="help-block" element="em" />
					</div>
				</div>
				</div>
				
				<!-- confirm password filed -->
				<!-- Enter Password  offset-md-1  col-md-offset-1 -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="confirmPassword">Confirm Password: </label>
				
					<div class="col-md-8" >
				
						<sf:password path="confirmPassword" id="confirmPassword" placeholder="Re-enter Password" class="form-control"/>
						<sf:errors path="confirmPassword" cssClass="help-block" element="em" />
					</div>
				</div>
				</div>
				
				<!--Select Role -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="role">Select Role: </label>
				
					<div class="col-md-8" >
				
					<label class="radio-inline">
					  <sf:radiobutton  path="role" value="USER" checked="checked"/>User
					</label>
						
					<label class="radio-inline">
					  <sf:radiobutton  path="role" value="SUPPLIER"/>Supplier
					</label>
						
						<sf:errors path="role" cssClass="help-block" element="em"/>
					</div>
					
				</div>
				</div>
				
				
				<!-- button -->
				<div class="form-group">
				<div class="row">
					<div class="col-md-auto offset-md-4">
				
						<button type="submit"  id="submit" value="Submit" class="btn btn-primary"
						 name="_eventId_billing">
						 Next - Billing &#160;
						 <i class="fa fa-chevron-right" style="font-size:16px;color:red"></i>
						</button>
					</div>
				</div>
				</div>

			</sf:form>
			
			</div>
		</div>
	</div>
	
	<div class="col-md-2"></div>
	
	</div>
		</div>
			
<%@include file="../shared/flows-footer.jsp" %>		