<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../shared/flows-header.jsp" %>
			
		<div class="container">
		<div class="row" >

	<div class="col-md-2"></div>
	<div class="col-md-8">
		<div class="card border-primary">
		
		<div class="card-header bg-primary " style="color: white;">SignUp - Address</div>

		<div class="card-body">
		
			<!-- Spring form -->
			
			<sf:form class="form-horizontal" 
			modelAttribute="address" 
			method="POST"
			id="registerForm"
			>
			
			<!-- Enter Product Name -->
				<div class="form-group">
				
					<div class="row">
						<label class="col-md-4 col-form-label" for="addressLineOne">Address Line One: </label>
						
						<div class="col-md-8">
				
							<sf:input type="text" path="addressLineOne" id="addressLineOne" placeholder="Enter Address Line One" class="form-control"/>
							
							<sf:errors path="addressLineOne" cssClass="help-block" element="em"/>
						
						</div>
						
					</div>
					
				</div>
				
				<!-- Enter Brand Name   offset-md-1  col-md-offset-1 -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="addressLineOne">Address Line One: </label>
				
					<div class="col-md-8" >
				
						<sf:input type="text" path="addressLineTwo" id="addressLineTwo" placeholder="Enter Address Line Two" class="form-control"/>
						<sf:errors path="addressLineTwo" cssClass="help-block" element="em" />
					</div>
				</div>
				</div>
				
				<!-- Enter Email   offset-md-1  col-md-offset-1 -->
				<div class="form-group">
					<div class="row">
						<label class="col-md-4 col-form-label" for="city">City: </label>
					
						<div class="col-md-8" >
					
							<sf:input type="text" path="city" id="city" placeholder="Enter City Name" class="form-control"/>
							<sf:errors path="city" cssClass="help-block" element="em" />
							
						</div>
					</div>
				</div>
				
				<!-- Enter unitPrice  offset-md-1  col-md-offset-1 -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="contactNumber">Postal Code: </label>
				
					<div class="col-md-8" >
				
						<sf:input type="number" path="postalCode" id="postalCode" placeholder="XXXXXX" class="form-control"/>
						<sf:errors path="postalCode" cssClass="help-block" element="em" />
					
					</div>
				</div>
				</div>
				
				<!-- Enter Password  offset-md-1  col-md-offset-1 -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="state">State: </label>
				
					<div class="col-md-8" >
				
						<sf:input path="state" id="state" placeholder="State" class="form-control"/>
						<sf:errors path="state" cssClass="help-block" element="em" />
					</div>
				</div>
				</div>
				
				
				<!--Select Role -->
				<div class="form-group">
				<div class="row">
				<label class="col-md-4 col-form-label" for="country">Country: </label>
				
					<div class="col-md-8" >
				
						<sf:input path="country" id="country" placeholder="Enter Country" class="form-control"/>
						<sf:errors path="country" cssClass="help-block" element="em" />
					</div>
				</div>
				</div>
				
				
				<!-- button -->
				<div class="form-group">
				<div class="row">
					<div class="col-md-auto offset-md-4">
				
						<button type="submit"  id="submit" value="Submit" class="btn btn-primary"
						 name="_eventId_personal">
						 <i class="fa fa-chevron-left" style="font-size:16px;color:red"></i>
						 Previous - Personal &#160;
						 
						</button>
						
						<button type="submit"  id="submit" value="Submit" class="btn btn-primary"
						 name="_eventId_confirm">
						 Next - Confirm &#160;
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