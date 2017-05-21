utkalWaterHome.controller('orderHistoryController',function($window,$scope,$uibModal,$http,$state,$timeout,orderHistoryService,orderService){
	

//populate the customer List
orderService.loadCustomerList().then(function(data){
	$("#loadingMessage").show();
	  //play with the data
	  if(data.status == 200){
		  $scope.names=data.data;
		  $scope.customers=data.data;
	  }
	  $timeout(function(){$("#loadingMessage").hide();}, 300);
});




$scope.selectedCustomer = undefined;
$scope.formatLabel = function(model) {
	  if($scope.customers != undefined){
	 for (var i=0; i< $scope.customers.length; i++) {
if (model === $scope.customers[i].customerId) {
	$('#dateDiv').show();
	$('#btnDiv').show();
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
		  
		// Calender open function	
			 $scope.openCalender2 = function() {
				    $scope.popup2.opened = true;
				  };
			
				  $scope.formats = ['yyyy-MM-dd','dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
				  $scope.format = $scope.formats[1];
				 // $scope.altInputFormats = ['yyyy/M!/d!'];
				  $scope.popup2 = {
						    opened: false
						  };
						
		//
		

$scope.showOrderHistory = function(){
	//$scope.orderHistoryObject={};
              if($scope.orderHistoryObject.startDate != undefined && $scope.orderHistoryObject.endDate != undefined)	{
				  $("#loadingMessage").show();
	var normalJarordered=0;
	var normalJarReturned=0;
	var normalJarReturnedFilled=0;
	var coldJarOrdered=0;
	var coldJarReturned=0;
	var coldJarReturnedFilled=0;
	var containerOrdered=0;
	var containerReturned=0;
	var totalBill=0;
	var totalPayement=0;

	$scope.orderHistoryObject.customerId=$scope.selectedCustomer;//$scope.names.key;
	orderHistoryService.getOrderHistory($scope.orderHistoryObject).then(function(data){
   	  //play with the data
		  if(data.status == 200){
			  	normalJarordered=0;
				normalJarReturned=0;
				normalJarReturnedFilled=0;
				coldJarOrdered=0;
				coldJarReturned=0;
				coldJarReturnedFilled=0;
				containerOrdered=0;
				containerReturned=0;
				totalBill=0;
				totalPayement=0;
				
			  $scope.orderHistory=data.data;
			  $scope.names.key=$scope.orderHistoryObject.customerId;

			  angular.forEach(data.data, function(item){
				  normalJarordered=normalJarordered+parseInt(item.normalWaterJarOrder);
                  normalJarReturned=normalJarReturned+parseInt(item.normalWaterJarReturnedEmpty);
				  normalJarReturnedFilled=normalJarReturnedFilled+parseInt(item.normalWaterJarReturnedFilled);
                  coldJarOrdered=coldJarOrdered+parseInt(item.coldWaterJarOrder);
                  coldJarReturned=coldJarReturned+parseInt(item.coldWaterJarReturnedEmpty);
				  coldJarReturnedFilled=coldJarReturnedFilled+parseInt(item.coldWaterJarReturnedFilled);
				  containerOrdered=containerOrdered+parseInt(item.containerOrdered);
				  containerReturned=containerReturned+parseInt(item.containerReturned);
                  totalBill=totalBill+parseInt(item.totalBill);
                  totalPayement=totalPayement+parseInt(item.paymentRcvd);
               });

				$scope.normalJarOrdered=normalJarordered;
				$scope.normalJarReturned=normalJarReturned;
				$scope.normalWaterReturnedFilled=normalJarReturnedFilled;
				$scope.coldJarOrdered=coldJarOrdered;
				$scope.coldJarReturned=coldJarReturned;
				$scope.coldJarReturnedFilled=coldJarReturnedFilled;
				$scope.containerOrdered=containerOrdered;
				$scope.containerReturned=containerReturned;
				$scope.totalBill=totalBill;
				$scope.totalPayement=totalPayement;

			  $('#orderHistoryResultTable').show();
			  $timeout(function(){$("#loadingMessage").hide();}, 300);
		  }
	});
	
}
	
};


$scope.printInvoice = function () {
                var $popup = $window.open("./html/order/orderInvoice.html", "popup", "width=650,height=600,left=80,top=150");
                $popup.customerId = $scope.names.key;
				$popup.startDate=$scope.orderHistoryObject.startDate;
				$popup.endDate=$scope.orderHistoryObject.endDate;
            };






});




utkalWaterHome.controller('orderDetailsByDate',function($window,$scope,$uibModal,$http,$state,$timeout,orderHistoryService,orderService){
	
	
	

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
		  
		// Calender open function	
			 $scope.openCalender2 = function() {
				    $scope.popup2.opened = true;
				  };
			
				  $scope.formats = ['yyyy-MM-dd','dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
				  $scope.format = $scope.formats[1];
				 // $scope.altInputFormats = ['yyyy/M!/d!'];
				  $scope.popup2 = {
						    opened: false
						  };
						
		//
		
		
		
		$scope.getOrderDetailsByDt = function(){
						
			if($scope.orderDetails.startDate != undefined && $scope.orderDetails.endDate != undefined){
					$("#loadingMessage").show();
               orderHistoryService.getOrderDetailsByDate($scope.orderDetails).then(function(data){
					 $scope.orderHistory=data.data;
					 
					 $scope.normalJarOrderedCount=0;
					 $scope.coldJarOrderedCount=0;
                     $scope.normalJarReturnedFilledCount = 0;
                     $scope.coldJarReturnedFilledCount = 0;
                     $scope.normalJarReturnedEmptyCount=0;
					 $scope.coldJarReturnedEmptyCount=0;
                     $scope.containerOrderedCount=0;
                     $scope.containerReturnedCount=0;
                     $scope.totalBillCount=0;
$scope.paymentRecvdCount=0;
                     
 angular.forEach(data.data, function(item){
   $scope.normalJarOrderedCount = $scope.normalJarOrderedCount+ parseInt(item.normalWaterJarOrder);
$scope.normalJarReturnedFilledCount=$scope.normalJarReturnedFilledCount + parseInt(item.normalWaterJarReturnedFilled);
$scope.normalJarReturnedEmptyCount=$scope.normalJarReturnedEmptyCount+ parseInt(item.normalWaterJarReturnedEmpty);
$scope.coldJarOrderedCount=$scope.coldJarOrderedCount+ parseInt(item.coldWaterJarOrder);
 $scope.coldJarReturnedFilledCount = $scope.coldJarReturnedFilledCount + parseInt(item.coldWaterJarReturnedFilled);
$scope.coldJarReturnedEmptyCount=$scope.coldJarReturnedEmptyCount+ parseInt(item.coldWaterJarReturnedEmpty);
$scope.containerOrderedCount=$scope.containerOrderedCount + parseInt(item.containerOrdered);
$scope.containerReturnedCount=$scope.containerReturnedCount+parseInt(item.containerReturned);
$scope.totalBillCount=$scope.totalBillCount+ parseInt(item.totalBill);
$scope.paymentRecvdCount=$scope.paymentRecvdCount+parseInt(item.paymentRcvd);
});
				   });
		   
			   $timeout(function(){//$('#orderDetailsTable').DataTable(); 
			   $("#orderDetailsTableDiv").show();$("#loadingMessage").hide();}, 1000);
			}
			
		};
		
		
		

});