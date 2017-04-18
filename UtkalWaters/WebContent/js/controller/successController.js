utkalWaterHome.controller('successController',function($scope,$http,$state,$stateParams){
	
	var message = $stateParams.message+$stateParams.value;
	$scope.dt=message;
	
	
	
	
});