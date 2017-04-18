utkalWaterHome.controller('editOrderController',function($scope,$http,$state,$timeout,orderService,defaulterService){
	//populate the customer List
orderService.loadCustomerList().then(function(data){
	$("#loadingMessage").show();  
	//play with the data
	  if(data.status == 200){
		  $scope.customers=data.data;
	  }
	  $timeout(function(){$("#loadingMessage").hide();}, 300); 
});

 
$scope.selectedCustomer = undefined;
$scope.selectedCustomerName=undefined;
$scope.formatLabel = function(model) {
	  if($scope.customers != undefined){
	 for (var i=0; i< $scope.customers.length; i++) {
if (model === $scope.customers[i].customerId) {
	$('#dateDiv').show();
	$('#btnDiv').show();
	$scope.selectedCustomerName=$scope.customers[i].customerName;
	     return $scope.customers[i].customerName;
}
}
	  }
};


//on customer name change function:
	$scope.onCustomerNameChange = function(){
		var key = $scope.names.key;
				$('#dateDiv').show();
		$('#btnDiv').show();
		//$('#customerName').prop('disabled',true);
	
	};
	//End onCustomer Name change

	
	
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
				
//


$scope.getDetailsByCustomer = function(){
	$("#loadingMessage").show();  
	$scope.order.customerId=$scope.selectedCustomer;
	var tempDate = $scope.order.orderDate;
	
	orderService.loadCustomerDetails($scope.order.customerId).then(function(data){	
	
			if(data.status == 200){
				$scope.customer=data.data;
				$scope.customerName=data.data.customerName;
			}
		});
	
	
	defaulterService.getPrevDetailsByCustomer($scope.order.customerId).then(function(data){	
		if(data.status == 200){
		$scope.prevDtls=data.data;
		}
	});
	
	
	 orderService.getOrderDetailsByDateAndCustomerId($scope.order).then(function(data){
		 
		 if(data.status == 200 ){
					  $scope.order = data.data;
				  
					  $scope.order.orderDate = tempDate;
				  }else {
					  $state.go('successMessage',{'message':'Error Occured during creating the customer order ','value':''});
				  }
		 });
		 
	 if($scope.customerName == ""){
		 
			alert("No Such Customer Exists.");
		}else{
			$('#editTableDiv').show();
		}
		
	  $timeout(function(){$("#loadingMessage").hide();}, 300);
	 
};
	
	
	// Edit form enableExternalCapture
	$scope.enableEdit = function(){
		$('#editForm').prop('disabled',false);
					  $scope.normalWaterJarOrderServer=$scope.order.normalWaterJarOrder;
					  $scope.normalWaterJarReturnedEmptyServer=$scope.order.normalWaterJarReturnedEmpty;
					  $scope.normalWaterJarReturnedFilledServer=$scope.order.normalWaterJarReturnedFilled;
					  
					  $scope.coldWaterJarOrderServer=$scope.order.coldWaterJarOrder;
					  $scope.coldWaterJarReturnedEmptyServer=$scope.order.coldWaterJarReturnedEmpty;
					  $scope.coldWaterJarReturnedFilledServer=$scope.order.coldWaterJarReturnedFilled;
					  $scope.containerOrderedServer=$scope.order.containerOrdered;
					  $scope.containerReturnedServer=$scope.order.containerReturned;
					  $scope.totalBillServer=$scope.order.totalBill;
					  $scope.paymentRcvdServer=$scope.order.paymentRcvd;
					  
		//once the edit button is enabled change the values...
		//$scope.normalWaterJarOrderPrev=$scope.order.normalWaterJarOrder;
		/*$scope.order.normalWaterJarOrder="0";
		$scope.order.coldWaterJarOrder="0";
		$scope.order.coldWaterJarReturnedEmpty="0";
		$scope.order.coldWaterJarReturnedFilled="0";
		$scope.order.normalWaterJarReturnedEmpty="0";
		$scope.order.normalWaterJarReturnedFilled="0";
		$scope.order.containerOrdered="0";
		$scope.order.containerReturned="0";
		$scope.order.totalBill="0";
		$scope.order.paymentRcvd="0";*/
		
		
		$('#submitDiv').show();
		$('#editButtonDiv').hide();
		//$('#customerName').prop('disabled',true);
		
	};
	
	$scope.cancel= function(){
		//$scope.names.key="";
		$scope.order.orderDate=""; 
		$('#dateDiv').hide();
		$('#btnDiv').hide();
		$('#editTableDiv').hide();
		
	};
	
	
	$scope.editCustomerOrder = function(){
		$("#loadingMessage").show();
		$scope.calculateTotal();
		$scope.prevDtls.customerId=$scope.order.customerId;		
		$scope.prevDtls.prevColdJarPending=parseInt($scope.prevDtls.prevColdJarPending)+(parseInt($scope.order.coldWaterJarOrder)-parseInt($scope.coldWaterJarOrderServer)) - (parseInt($scope.order.coldWaterJarReturnedEmpty)-parseInt($scope.coldWaterJarReturnedEmptyServer)) - (parseInt($scope.order.coldWaterJarReturnedFilled)-parseInt($scope.coldWaterJarReturnedFilledServer));
		$scope.prevDtls.prevNormalJarPending=parseInt($scope.prevDtls.prevNormalJarPending)+(parseInt($scope.order.normalWaterJarOrder)-parseInt($scope.normalWaterJarOrderServer)) - (parseInt($scope.order.normalWaterJarReturnedEmpty)-parseInt($scope.normalWaterJarReturnedEmptyServer)) - (parseInt($scope.order.normalWaterJarReturnedFilled)-parseInt($scope.normalWaterJarReturnedFilledServer));
		$scope.prevDtls.prevPaymentDue=parseInt($scope.prevDtls.prevPaymentDue)+(parseInt($scope.order.totalBill)-parseInt($scope.totalBillServer)) - (parseInt($scope.order.paymentRcvd)-parseInt($scope.paymentRcvdServer));
		$scope.prevDtls.prevContainerPending=parseInt($scope.prevDtls.prevContainerPending)+(parseInt($scope.order.containerOrdered)-parseInt($scope.containerOrderedServer)) - (parseInt($scope.order.containerReturned)-parseInt($scope.containerReturnedServer));
		$scope.prevDtls.customerName=$scope.selectedCustomerName;//$scope.names[$scope.names.key];
		
		orderService.editCustomerOrder($scope.order).then(function(data){
				  $scope.order = data.data;
				  
				   if(data.status == 200 && data.data.result ==1){
					        //save the pending records
					   defaulterService.savePendingDetails($scope.prevDtls).then(function(data){
						   if(data.status == 200){
						       $scope.order={};
						       $timeout(function(){$("#loadingMessage").hide();}, 300);
					           $state.go('successMessage',{'message':'Order has been completed for the Customer Id : ','value':data.data.customerId});
					  	      }else {
					  	    	  $timeout(function(){$("#loadingMessage").hide();}, 300);
					               $state.go('successMessage',{'message':'Error Occured during creating the customer order ','value':''});
				                  }
						   
					   });
					  
				  }else {
					  $timeout(function(){$("#loadingMessage").hide();}, 300);
					  $state.go('successMessage',{'message':'Error Occured during creating the customer order ','value':''});
				  }
				  
			  });
		 
	};
	
	
	
	
	$scope.calculateTotal=function(){
		
		$scope.order.totalBill=$scope.order.normalWaterJarOrder*$scope.customer.normalJarRate+$scope.order.coldWaterJarOrder*$scope.customer.coldJarRate-$scope.order.normalWaterJarReturnedFilled*$scope.customer.normalJarRate-$scope.order.coldWaterJarReturnedFilled*$scope.customer.coldJarRate;
	};
	
	
});