$(function() {
	// solving the active menu problem
	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;

	case 'Contact Us':
		$('#contact').addClass('active');
		break;

	case 'All Products':
		$('#listProducts').addClass('active');
		break;

	case 'Home':
		$('#home').addClass('active');
		break;
	
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
		
	case 'User Cart':
		$('#userCart').addClass('active');
		break;	
	
	default:
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	//to tackle the csrf token //activation deactivation problem resolution
	var token =$('meta[name="_csrf"]').attr('content');
	var header =$('meta[name="_csrf_header"]').attr('content');
	
	if (token.length>0 && header.length>0) {
		//set the token header for the ajax request
		$(document).ajaxSend(function(e,xhr,options){
			xhr.setRequestHeader(header,token);
		});
	}
	
	//end tackle the csrf token
	
	// code for Jquery dataTable

	// dummy dataset
	/*
	 * //create dataset
	 * 
	 * var products=[ ['1','amar'], ['2','deepak'], ['3','shirdhart'],
	 * ['4','prem'], ['5','raju'], ['6','raman'], ['7','ram'], ['8','shiyam'],
	 * ['9','sanjay'], ['10','ramesh'], ['11','ramu'], ['12','ranjan'],
	 * ['13','soni'], ['14','moni'], ['15','toni'] ];
	 */

	// code for Jquery dataTable
	
	var $table = $("#productListTable");
	// execute below code only where we have this table
	if ($table.length) {
		// console.log('Inside the table');

		var jsonUrl = '';

		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
			
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
			//console.log(jsonUrl);
		}
		
		// now we are going to use datatable funtion
		$table.DataTable(
				// DataTable consist of an object

				{
					// now i am passing a parameter call data, from where it
					// will get the data, we have created a data called
					// 'products'

					lengthMenu : [
							[ 3, 5, 10, -1 ],
							[ "3 Records", "5 Records", "10 Records", "All Records" ] ],
					pageLength : 5,
					// now i dont want to use dummy data
					// data:products

					// woking on real data
					ajax : {
						// first property
						url : jsonUrl,

						// collection of objects without any name
						// 2nd property
						dataSrc : ''
					},
					// columns is nothing but collection of objects, having many
					// property
					columns : [
					//to show images i did a trick
					{
						
						data:'code',
						bSortable:false,
						mRender:function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" width="100" height="100" class="dataTableImage">' ;		
						}
							
					}, {
						// first property right hand data is should be same as
						// from json data properties
						
						data : 'name'
					}, {
						
						data : 'brand'
					}, {
						
						data : 'unitPrice',
						//mRender funtion has 3 param
						//actualValue,dataType,entire row
						mRender:function(data,type,row){
							return '&#8377; ' + data
						}
							
					}, {
						
						data : 'quantity',
							mRender:function(data,type,row){			
								
								if (data<1) {
										return '<span style="color:red">Out of stack!</span>';
								} 
									return data;
								
							}
							
					}, 
					//now i want extra column(view the product and add it to the cart)
					//so we need product id
					//first column
					{
						data : 'id',
						//disable shorting icon
						bSortable:false,
						mRender:function(data,type,row){
							//we cant use url: here because it direct send request to given url without click
							//url:contextRoot+'/show/{data}/product'  
							//thats why we will use ancher tag
							//return '<a href="'+window.contextRoot+'/show/'+data+'/product"><button>View</button></a>';
							var str='';
							str+='<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><i class="fa fa fa-eye fa-2x" ></i></a> &#160;  &#160;';
							if (row.quantity<1) {
								
								str+='<a href="javascript:void(0)" class="btn btn-success disabled"><strike><i class="fa fa-cart-plus fa-2x" aria-hidden="true"></i></strike></a>';
							
							} else {
								if (userRole=='ADMIN') {
									str+='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i></a>';
								}
								else {
									str+='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><i class="fa fa-cart-plus fa-2x" ></i></a>';	
								}
								
							}
							
							return str;
						}
					},
					//second column
					/*{
						data : 'id',
						mRender:function(data,type,row){
							//return '<a href="'+window.contextRoot+'/cart/add/'+data+'/product"><button>Add</button></a>';
						}
					}*/
					
					]
					

				});
	}
	
	/*dismissing alert after 3 seconds*/
	
	var $alert = $('.alert');
	
	
		if ($alert.length) {
			
			setTimeout(function(){$alert.fadeOut('slow');},3000)
				
			
		}
		
	/*managing toggle switch*/
	

	/*Data Table For Admin start*/

	var $adminProductsTable = $("#adminProductsTable");
	// execute below code only where we have this table
	if ($adminProductsTable.length) {
		// console.log('Inside the table');

		var jsonUrl = window.contextRoot+'/json/data/admin/all/products';
	
		// now we are going to use datatable funtion
		$adminProductsTable.DataTable(
				// DataTable consist of an object

				{
					  responsive: true,
					// now i am passing a parameter call data, from where it
					// will get the data, we have created a data called
					// 'products'

					lengthMenu : [
							[ 10, 20, 30,,50, -1 ],
							[ "10 Records", "20 Records", "30 Records", "50 Records", "All Records" ] ],
					pageLength : 10,
					// now i dont want to use dummy data
					// data:products

					// woking on real data
					ajax : {
						// first property
						url : jsonUrl,

						// collection of objects without any name
						// 2nd property
						dataSrc : ''
					},
					// columns is nothing but collection of objects, having many
					// property
					columns : [
					{
						data:'id'
					},
					{
						
						data:'code',
						bSortable:false,
						mRender:function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" width="100" height="100" class="adminTableDataImg">' ;		
						}
							
					}, {
						// first property right hand data is should be same as
						// from json data properties
						
						data : 'name'
					}, {
						
						data : 'brand'
					},
					{
						
						data : 'quantity',
							mRender:function(data,type,row){			
								
								if (data<1) {
										return '<span style="color:red">Out of stack!</span>';
								} 
									return data;
								
							}
							
					}, 
					{
						
						data : 'unitPrice',
						
						//actualValue,dataType,entire row
						mRender:function(data,type,row){
							return '&#8377;' + data
						}
							
					},
					{
						data : 'active',
						bSortable: false,
						mRender:function(data,type,row){
						
						var str = '';
							
						str=str + '<label class="switch">';
						
						// toggle switch
						if (data) {
							str=str + '<input type="checkbox" checked="checked" value="'+row.id+'" />';
							
						  			
						} else {
							str=str + '<input type="checkbox" value="'+row.id+'" />';
						}
						str=str + '<div class="slider round"></div></label>';
						return str;
						}
						
						
						
					},{
						data:'id',
						bSortable:false,
						mRender:function(data,type,row){
							
							var str = '';
							
							
							str+='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
							 str+= '<i class="fa fa-pencil" aria-hidden="true"></i></a>';
							return str;
						}
						
						
					}
					
					],
					initComplete:function(){
						
						var api = this.api();
						api.$('.switch input[type="checkbox"]').on('change',function(){
							
							var checkbox = $(this);
							var checked  = checkbox.prop('checked');
							
							var mMsg = (checked) ? 'You want to activate the product?':'You want to deactivate the product?';
							
							var value = checkbox.prop('value');
							
							//now we will use bootbox
							
							bootbox.confirm({
								
								size:'medium',
								title:'Product Activation & Product Deactivation',
								message:mMsg,
								closeButton : false,
								callback: function(confirmed){
									if(confirmed){
										
										console.log(value);
										var activationUrl = window.contextRoot+'/manage/product/'+value+'/activation';
										
										$.post(activationUrl,function(data){
									
											bootbox.alert({
												
												size:'medium',
												title:'Information',
												closeButton : false,
												message:data
												
												});
											
										});
										
									
										
									}else{
										checkbox.prop('checked',!checked);
									}
								}
							
							});
						});

					}
					

				});
	}

	/*Data Table for Admin ends*/
	
	/*validation code for category*/

	var $categoryForm = $("#categoryForm");
	
	if ($categoryForm.length) {
		
		$categoryForm.validate({
			
			rules:{
				
				name : {
					required:true,
					minlength:2
				},
				description : {
					
					required:true,
					
				}
			},
			
			messages : {
				
				name: {
					required : 'Please add the category name!',
					minlength : 'the category name should not be less than 2 characters!'
				},
				description : {
					required : 'Please add a description for this category!'
				}
			},
			errorElement : 'em',
			errorPlacement: function(error,element){
				// add the class of help-block
				error.addClass('help-block');  
			
				// add the error element after the input element
				error.insertAfter(element);
				
			}
			
		});
	}
	//end validation code for category
	
	/*validation code for loginForm*/

	var $loginForm = $("#loginForm");
	
	if ($loginForm.length) {
		
		$loginForm.validate({
			
			rules:{
				
				username : {
					
					required:true,
					email:true
					
				},
				
				password : {
					
					required:true
					
				}
			},
			
			messages : {
				
				username: {
					required : 'Please enter username!',
					email:'Please enter a valid username!'
				},
				password : {
					required : 'Please enter password!'
				}
			},
			errorElement : 'em',
			errorPlacement: function(error,element){
				// add the class of help-block
				error.addClass('help-block');  
			
				// add the error element after the input element
				error.insertAfter(element);
				
			}
			
		});
	}
	//end validation code for loginForm
	
	//Handling the event of refreshCart Button of cart.jsp page
	
	var $cartLineId = $('button[name="refreshCart"]');
	
	$cartLineId.click(function(){
		
		//getting cartLineid
		var cartLineId = $(this).attr('value');
		
		//product count form db
		var dbProductQuant = $('#count_'+cartLineId).attr('value');
		//new product count after change
		var newProductQuant = $('#count_'+cartLineId).val();
		
		//work only when the count change in the CartLineElement then only do some work on it
		if (newProductQuant!=dbProductQuant) {
			//console.log("current count is "+newProductQuant  );
			//console.log("db dbProductQuant count is "+dbProductQuant  );
			
			if (newProductQuant < 1 || newProductQuant> 3) {
				//reverting back to the original quantity
				$('#count_'+cartLineId).val(dbProductQuant);
				//alert
				var dialog = bootbox.alert({
					closeButton:false,
					size:'medium',
					title:'Error',
					message:'Max 3 Quantity you can purchase!',
					backdrop: true,
					//className: 'bb-alternate-modal'
				});
				console.log("hurry i am working1");
				dialog.init(function(){
					console.log("hurry i am working");
					//$(this).attr("id", "colorRed");
					//$(this).css("color","red");
					$(this).find('.bootbox-body').css("color","red");
					//$(this).find('.bootbox-body').text(new Date());
					});
				
				/*dialog.on(".shown.bs.modal", function() {
					console.log("hellow am woking");
					  dialog.attr("id", "costumModal29");
					});*/
				
			}else{
				//forward to particular request window.contextRoot in js and conextRoot in jsp
				var updateUri = window.contextRoot + '/cart/' + cartLineId + '/udpate?Quant='+newProductQuant;
				//forward it to the controller
				
				window.location.href = updateUri;
				
			}
			
		}
	});
	
	//end of event of refreshCart
	
});
