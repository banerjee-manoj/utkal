var app = angular.module('printApp', []);
     app.controller('printController', function ($http,$scope, $window,printService) {
         $scope.customerId = $window.customerId;
		 $scope.startDate = $window.startDate;
		 $scope.endDate = $window.endDate;
  
		 //get the customer basic details.
		 $http({
			method : 'GET',
			url : hostname+'customer/customerDetails/'+$scope.customerId+'',
			headers : {'Content-Type': 'application/json','Accept' :'application/json'},
		}).success(function(data){
			$scope.customer=data;
		});
		$scope.orderHistoryObject={};
		$scope.orderHistoryObject.customerId=$scope.customerId;
		$scope.orderHistoryObject.startDate=$scope.startDate;
		$scope.orderHistoryObject.endDate= $scope.endDate;
		//Get the customer order details:
		
		printService.getOrderHistory($scope.orderHistoryObject).then(function(data){
   	  //play with the data
		  if(data.status == 200){
			  	normalJarordered=0;
				normalJarReturned=0;
				coldJarOrdered=0;
				coldJarReturned=0;
				totalBill=0;
				totalPayement=0;
				normalJarReturnedFilled=0;
				coldJarReturnedFilled=0;
				
			  $scope.orderHistory=data.data;
			 // $scope.names.key=$scope.orderHistoryObject.customerId;

			  angular.forEach(data.data, function(item){
				  normalJarordered=normalJarordered+parseInt(item.normalWaterJarOrder);
                  normalJarReturned=normalJarReturned+parseInt(item.normalWaterJarReturnedEmpty);
                  coldJarOrdered=coldJarOrdered+parseInt(item.coldWaterJarOrder);
                  coldJarReturned=coldJarReturned+parseInt(item.coldWaterJarReturnedEmpty);
                  totalBill=totalBill+parseInt(item.totalBill);
                  totalPayement=totalPayement+parseInt(item.paymentRcvd);
				   normalJarReturnedFilled=normalJarReturnedFilled+parseInt(item.normalWaterJarReturnedFilled);
				   coldJarReturnedFilled=coldJarReturnedFilled+parseInt(item.coldWaterJarReturnedFilled);
               });

				$scope.normalJarOrdered=normalJarordered;
				$scope.normalJarReturned=normalJarReturned;
				$scope.coldJarOrdered=coldJarOrdered;
				$scope.coldJarReturned=coldJarReturned;
				$scope.totalBill=totalBill;
				$scope.totalPayement=totalPayement;
				$scope.normalWaterReturnedFilled=normalJarReturnedFilled;
				$scope.coldJarReturnedFilled=coldJarReturnedFilled;
                $scope.finalDue= ($scope.customer.normalJarRate*$scope.normalJarOrdered+ $scope.customer.coldJarRate*$scope.coldJarOrdered)-
				($scope.customer.normalJarRate*$scope.normalWaterReturnedFilled + $scope.customer.coldJarRate*$scope.coldJarReturnedFilled)-$scope.totalPayement;
			  
		  }
	});
		 
		
		 /**
		  * open the window to print the content.
		  */
		 $scope.print = function () {
        	 
        	 
  var docHead = document.head.outerHTML;
  var printContents = document.getElementById('printArea').outerHTML;
  var winAttr = "location=no, statusbar=no, menubar=no, titlebar=no, toolbar=no,dependent=no, width=950, height=700, resizable=yes, screenX=200, screenY=200, personalbar=no, scrollbars=yes";
  var newWin = window.open("", "_blank", winAttr);
  var writeDoc = newWin.document;
  writeDoc.open();
  writeDoc.write('<!doctype html><html>' + docHead + '<body onLoad="window.print()">' + printContents + '</body></html>');
  writeDoc.close();
  newWin.focus();
  
			 /*var printContents = document.getElementById('printArea').innerHTML;
  var popupWin = window.open('', '_blank', 'width=300,height=300');
  popupWin.document.open();
  popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="style.css" /></head><body onload="window.print()">' + printContents + '</body></html>');
  popupWin.document.close();*/
       	  
         };
		  $scope.cancel = function () {
			  $window.close();
    $uibModalInstance.dismiss('cancel');
  };
		 
		 $scope.printDiv = function(divName) {
  
} 
		 
		 
     });
	
//});