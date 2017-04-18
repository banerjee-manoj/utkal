
utkalWaterHome.controller('defaulterController',function($scope,$state,$timeout,defaulterService){
	
	
	
	
	
	$scope.getPaymentDefaulterList=function(){	
	defaulterService.paymentDefaulterList().then(function(data){			
		if(data.status == 200){
             $scope.defaulters=data.data;
             $("#defaulterTable").show();
		}
	});
	
};
$scope.getJarDefaulterList=function(){	
	$("#loadingMessage").show();
	defaulterService.defaulterList().then(function(data){			
		if(data.status == 200){
             $scope.jarDefaulters=data.data;
             $("#jarDefaulterTable").show();
		}
	});
	 
	 $timeout(function(){$("#loadingMessage").hide();$('#defaulterTable').DataTable();}, 2000); 
	
};
});


