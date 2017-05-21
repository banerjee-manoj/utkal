utkalWaterHome.controller('deleteMessageController',function($scope,$http,$state,$stateParams){
	
	var message = $stateParams.message+$stateParams.value;
	$scope.message=message;
	
	
	
	$scope.back = function(){
		
		$state.go('showAllCustomer');
		
	};
});