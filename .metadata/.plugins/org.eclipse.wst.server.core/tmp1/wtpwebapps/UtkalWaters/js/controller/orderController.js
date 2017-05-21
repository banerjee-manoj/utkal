
utkalWaterHome.controller('newOrderController',function($scope,$http,$state,orderService){

	$scope.order={};


	
	//populate the customer List
	 orderService.loadCustomerList().then(function(data){
    	  //play with the data
		  if(data.status == 200){
			  $scope.names=data.data;
		  }
	});
	
	
	//on customer name change function:
	$scope.onCustomerNameChange = function(){
		var key = $scope.names.key;
	   $scope.order.customerId=$scope.names.key;
		orderService.loadCustomerDetails(key).then(function(data){	
			if(data.status == 200){
			$scope.customer=data.data;
			$('#orderTable').show();
			$('#newOrder').prop('disabled',true);
			$('#customerName').prop('disabled',true);
			}
		});
		
		orderService.getPaymentDetails(key).then(function(orderData){			
			if(orderData.status == 200){
			$scope.order=orderData.data;
			$scope.normalWaterJarPending=orderData.data.normalWaterJarPending;
			$scope.coldWaterJarPending=orderData.data.coldWaterJarPending;
			$scope.customer.prevContainer=orderData.data.containerPending;
			$scope.containerReturn=0;
			$scope.containerOrder=0;
			$('#orderTable').show();
			$('#newOrder').prop('disabled',true);
			$scope.calculateTotal();			
			}
		});
		
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
		  

		  $scope.calculateContainer=function(){
			  $scope.order.containerPending=$scope.customer.prevContainer+$scope.containerOrder-$scope.containerReturn;
		  };
		  
		  
		  //Update the toal and other values in the order page:
		  $scope.calculateTotal = function(){
			    $scope.calculateNormalJar();
						  $scope.calculateColdJar();
						  $scope.calculateCurrentBill();
						  $scope.calculatereturnAmmount();
			 // $scope.order.totalBill =parseInt($scope.order.previousDue)+ $scope.order.normalWaterJarOrder*$scope.order.normalWaterRate+$scope.order.coldWaterJarOrder*$scope.order.coldWaterRate;
			   $scope.order.totalBill=$scope.currBill+parseInt($scope.order.previousDue)-parseInt($scope.returnAmmount);
			   $scope.calculateFinalNormalJars();
			   $scope.calculateDue();
			  
		  };
		  //
		  
		  //calculate due
		  $scope.calculateDue = function(){
			  $scope.order.outstandingAmmount=$scope.order.totalBill-$scope.order.paymentRcvd;
		  };
		  
		  		  
		  //normal jar calculate
		  $scope.calculateNormalJar = function(){
			  
			//$scope.normalWaterTotal  = $scope.order.normalWaterJarOrder*$scope.order.normalWaterRate;
			$scope.normalWaterTotal  = $scope.order.normalWaterJarOrder*$scope.customer.normalJarRate;
		  };
		  //normal jar calculate
		  $scope.calculateColdJar = function(){
			  
			//$scope.coldWaterTotal  = $scope.order.coldWaterJarOrder*$scope.order.coldWaterRate;
			$scope.coldWaterTotal  = $scope.order.coldWaterJarOrder*$scope.customer.coldJarRate;
		  };
		  
		  //for current bill
		   $scope.calculateCurrentBill = function(){
			  
			$scope.currBill  = $scope.normalWaterTotal+$scope.coldWaterTotal;
		  };
		  
            //calculate return
            $scope.calculatereturnAmmount = function(){
				//$scope.returnAmmount= $scope.order.normalWaterJarReturnedFilled*$scope.filleReturnRate+$scope.order.coldWaterJarReturnedFilled*$scope.coldfilleReturnRate;
				$scope.returnAmmount= $scope.order.normalWaterJarReturnedFilled*$scope.customer.normalJarRate+$scope.order.coldWaterJarReturnedFilled*$scope.customer.coldJarRate;
			};		

          // final jar count
            $scope.calculateFinalNormalJars = function(){
				$scope.order.normalWaterJarPending=parseInt($scope.normalWaterJarPending)+$scope.order.normalWaterJarOrder-$scope.order.normalWaterJarReturnedFilled-$scope.order.normalWaterJarReturnedEmpty;
				$scope.order.coldWaterJarPending=parseInt($scope.coldWaterJarPending)+$scope.order.coldWaterJarOrder-$scope.order.coldWaterJarReturnedFilled-$scope.order.colWaterJarReturnedEmpty;
			};		  
		  
		  
 //place new order method:
		  
		  $scope.placeOrder=function(){
			  orderService.newOrder($scope.order).then(function(data){
				  if(data.status == 200 && data.data.result ==1){
					  $scope.order={};
					  $state.go('successMessage',{'message':'Order has been completed for the Customer Id : ','value':data.data.customerId});
					  
				  }else {
					  $state.go('successMessage',{'message':'Error Occured during creating the customer order ','value':''});
				  }
			  });
			
			
			
		  };
		  
		  $scope.cancel=function(){
			  $state.go('home');
			  
		  };
	
	
});