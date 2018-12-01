
<div class="container">
	<div class="row">

		<!-- To display sidebar -->
		<div class="col-md-3">

			<%@include file="./shared/sidebar.jsp"%>

		</div>

		<!-- To display actual products -->
		<div class="col-md-9">
			<!-- added breadcrumb component -->
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllProducts==true}">
					<script>window.categoryId='';</script>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">All Products</li>
						</ol>
					</c:if>

					<c:if test="${userClickCategoryProducts==true}">
						<script>window.categoryId='${category.id}';</script>
						<ol class="breadcrumb">

							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item">Category</li>
							<li class="breadcrumb-item active">${category.name}</li>

						</ol>
					</c:if>
				</div>

			</div>


			<div class="row">

				<div class="col-xs-12">
				<!-- <div class="container-fluid">
				<div class="table-responsive"> -->
				<div style="overflow-x:auto;">
					<table id="productListTable" class="table table-bordered table-hover rounded" style="width:100%">
					<!-- table-striped  -->

						<thead>
							<tr>
							<!-- this is for dummy data -->
							<!-- 	<th>ID</th>
								<th>Name</th>	 -->
								
							<!-- this is for real data -->	
								<th></th> 
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Quantity</th>
						   <!-- <th>View</th> -->
						   <!-- <th>Add to Cart</th> -->
								<th></th> 
								
							</tr>
							
						</thead>

					</table>
					<!-- end of table -->
					</div>
					<!-- end of table-responsive -->
					<!-- </div> -->
					<!-- end of container-fluid -->			

				</div>
			<!-- end of col-xs-12 -->
			</div>

		</div>

	</div>
</div>
