<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../shared/flows-header.jsp" %>
			
	<div class="container-fluid">
	<div class="row" >

	
	<div class="col-md-6">
		<div class="card border-primary">
		
		<div class="card-header bg-primary " style="color: white;">Personal Details</div>

		<div class="card-body">
		<!-- code to display the personal detail -->
		
			<div class="text-center">
			
			<h4>${registerModel.user.firstName} ${registerModel.user.lastName}</h4>
			<h6>Email: ${registerModel.user.email} </h6>
			<h6>Contact Number: ${registerModel.user.contactNumber} </h6>
			<h6>Role: ${registerModel.user.role} </h6>
			</div>
			
		</div>
		
		<div class="card-footer">
			<!-- ancher to move to the edit of personal details -->
   			<a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary">Edit</a>
  		</div>
  		
		</div>
	</div>
	
	<div class="col-md-6">
		<div class="card border-primary">
		
		<div class="card-header bg-primary " style="color: white;">Billing Address</div>

		<div class="card-body">
		<!-- code to display the address detail -->
			<div class="text-center">
			
			<h6>${registerModel.address.addressLineOne} </h6>
			<h6>${registerModel.address.addressLineTwo} </h6>
			<h6>${registerModel.address.city} - ${registerModel.address.postalCode} </h6>
			<h5>${registerModel.address.state} - ${registerModel.address.country} </h5>
			
			</div>
		</div>
		
		<div class="card-footer">
   			<!-- ancher to move to the edit of address-->
   			<a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary">Edit</a>
  		</div>
  
		</div>
	</div>
	
	</div>
	
	<br/>
	<div class="row justify-content-md-center">
    <div class="col col-lg-4">
     	<div class="text-center">
     		<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-primary">Confirm</a>
     	</div>
    </div>
 	</div>
 	
		</div>
			
<%@include file="../shared/flows-footer.jsp" %>		