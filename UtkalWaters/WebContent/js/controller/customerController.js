
utkalWaterHome.controller('addCustomerController',function($scope,$state,customerService){
	$scope.customer={};
	$scope.customerTypes=[{'key':'regular','value':'Regular'},{'key':'catering','value':'Catering'}];
	$scope.customer.customerType='regular';
	
	$scope.addCustomer = function(){
		$scope.customer.activationDate=new Date($scope.customer.activationDate);
      customerService.addCustomer($scope.customer).then(function(data){
    	  //play with the data
		  if(data.data.result == 2){
			  alert("Customer Already Exists");
		  }else if(data.data.result == 1){
			  $scope.customer={};
			  $state.go('successMessage',{'message':'Customer '+ data.data.customerName+' Has Been Added Successfully' ,'value':''});
		  }else{
			  $scope.customer={};
			  $state.go('successMessage',{'message':'Fail To Add Customer' ,'value':''});
		  }
		
	});
	
	};
	
	
	// Calender open function	
	 $scope.openCalender = function() {
		    $scope.popup1.opened = true;
		  };
		  $scope.formats = ['yyyy-MM-dd','dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
		  $scope.format = $scope.formats[1];
		 // $scope.altInputFormats = ['yyyy/M!/d!'];
		  $scope.popup1 = {
				    opened: false
				  };
	
	
	
	
	
		
});




utkalWaterHome.controller("searchCustomerController",function($scope,$http,$state,$timeout,customerService){
	
	$scope.customer={};
	$scope.selectedCustomer='';
	$scope.customerTypes=[{'key':'regular','value':'Regular'},{'key':'catering','value':'Catering'}];
	/*$http({
		method : 'GET',
		//url : hostname+'http://localhost:8080/utkalRESTServices/customer/customerList',
		//url : hostname+'customer/customerListJson',
		url : hostname+'customer/customerList',
		headers : {'Content-Type': 'application/json','Accept' :'application/json'},
	}).success(function(data){
		//$("#spinner").hide();
		 $scope.names=data;
		// $scope.customers=data;
		 
	});
	*/
	
	
	// Calender open function	
	 $scope.openCalender = function() {
		    $scope.popup1.opened = true;
		  };
		  $scope.formats = ['yyyy-MM-dd','dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
		  $scope.format = $scope.formats[1];
		 // $scope.altInputFormats = ['yyyy/M!/d!'];
		  $scope.popup1 = {
				    opened: false
				  };
	
	
	
	
	
	$("#loadingMessage").show();
	$http({
		method : 'GET',
		url : hostname+'customer/customerListJson',
		headers : {'Content-Type': 'application/json','Accept' :'application/json','Authorization' :'Bearer ManojEncoded'},
	}).success(function(data){
		 $scope.customers=data;
	}).error(function(data,status){
		if(status == 401){
			 $state.go('successMessage',{'message':'Unauthorized User ','value':''});
		}
	});
	
	$timeout(function(){$("#loadingMessage").hide();}, 1000); 
	 
	 
	 $scope.selectedCustomer = undefined;
	  $scope.formatLabel = function(model) {
		  if($scope.customers != undefined){
		 for (var i=0; i< $scope.customers.length; i++) {
      if (model === $scope.customers[i].customerId) {
		     return $scope.customers[i].customerName;
      }
    }
		  }
  };
  
	/**
	*  This code is is also used for the autocomplete text boxes in angular js.
	*
	$scope.complete = function(string){
		
		var output = [];
		
		angular.forEach($scope.names,function(key,value){
			console.log(key);console.log("and");console.log(value);
		});
		
		
		angular.forEach($scope.names,function(country){
			if(country.toLowerCase().indexOf(string.toLowerCase()) >=0){
				output.push(country);
			}
		});
		
		$scope.filterCountry = output;
	};
	
	
	$scope.fillTextBox= function(countryData){
		
		$scope.customerNameText = countryData;
		$scope.hidethis=true;
	};
	*/
	
	$scope.onCustomerNameChange = function(){
	
		
		
		var key = $scope.selectedCustomer;//$scope.names.key;
		if(key != undefined){
			$("#loadingMessage").show();
		$http({
			method : 'GET',
			//url : 'http://localhost:8080/utkalRESTServices/customer/customerDetails/'+key+'',
			url : hostname+'customer/customerDetails/'+key+'',
			headers : {'Content-Type': 'application/json','Accept' :'application/json'},
		}).success(function(data){
			$scope.customer=data;
			$scope.customer.activationDate= new Date($scope.customer.activationDate);
			$('#resultTable').show();
			$('#editForm').prop('disabled',true);
			$('#submitDiv').hide();
			$('#editButtonDiv').show();
		 $timeout(function(){$("#loadingMessage").hide();}, 1000); 	
		});
		
		}else{
			alert("Please Select a customer ");
		}
		
	};
	
	
	$scope.enableEdit = function(){
		$('#editForm').prop('disabled',false);
		$('#submitDiv').show();
		$('#editButtonDiv').hide();
		//$('#customerName').prop('disabled',true);
		
	};
	
	$scope.cancel = function(){
		$('#customerName').prop('disabled',false);
		$('#resultTable').hide();
	};
	
	
	$scope.updateCustomerDetails = function(){
		
		customerService.updateCustomer($scope.customer).then(function(data){
			if(data.status=200){
				 $scope.customer={};
				  $state.go('successMessage',{'message':'Customer Has Been Updated Successfully - ' ,'value':data.data.customerName});
			}else{
				alert("Erroro Occured..");
			}
	    	  //play with the data
			 /* if(data.data.result == 0){
				  alert("Customer Already Exists");
			  }else {
				  $scope.customer={};
				  $state.go('successMessage',{'message':'Customer Has Been Added Successfully' ,'value':''});
			  }*/
			
		});
		
		
		/*//$scope.customer={};
		$http({
			method : 'POST',
			url : 'http://localhost:8080/utkalRESTServices/customer/updateCustomer',
			headers : {'Content-Type': 'application/json','Accept' :'application/json'},
			data : $scope.customer
		}).success(function(data){
			 if (!data) {
			      // if not successful, bind errors to error variables
alert('failed');
			 } else {
			      // if successful, bind success message to message
			//	 $("#spinner").hide();
				 alert("Success");
			 }
		});	*/
		
		
	};
	
	
	
});

utkalWaterHome.controller("showAllCustomerController",function($scope,$http,$state,$timeout,customerService){
	
	$("#loadingMessage").show();
	$http({
		method : 'GET',
		url : hostname+'customer/customerListJson',
		headers : {'Content-Type': 'application/json','Accept' :'application/json'},
	}).success(function(data){
//		$scope.drawTheDataTable(data);
		 $scope.customerList=data;
	});
	$timeout(function(){$("#loadingMessage").hide();$('#customerListTable').DataTable();}, 2000);
		
	
	
	$scope.deleteCustomer = function(customerId){
	     
		if(confirm("Are you Sure Want to delete this Customer ?")){
		 
		$("#loadingMessage").show();
		$http({
		method : 'GET',
		url : hostname+'customer/deleteCustomer/'+customerId,
		headers : {'Content-Type': 'application/json','Accept' :'application/json'},
	}).success(function(data){
					$timeout(function(){$("#loadingMessage").hide();}, 100);
$state.go('deleteMessage',{'message': data,'value':''});
		
	});
		}else{
		
	}  
	};
	
});






