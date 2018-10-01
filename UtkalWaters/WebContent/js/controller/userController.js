

utkalWaterHome.controller('createUserController',function($scope,$state,userService){
	
	$scope.user={};
	$scope.createUser = function(){
		userService.createUser($scope.user).then(function(data,status){
			if(data.status==200){
	 $state.go('successMessage',{'message':'User '+ data.data.userName+' Has Been Added Successfully' ,'value':''});	
			}else{
				  $state.go('successMessage',{'message':'Fail To Create User' ,'value':''});	
			}
		});
	};
	
	
});