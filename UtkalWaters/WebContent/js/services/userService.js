utkalWaterHome.service('userService',function($http,$rootScope){
	
	
this.createUser= function(user){
		
		return  $http.post(hostname+'user/createUser',user,
	{headers : {'Content-Type': 'application/json','Accept' :'application/json','AuthToken' : $rootScope.getToken()}})
	   .then(function success(response){
 
	   return response;
	},
	function error(response){
		 
		return response;
	}
	
	);
	
	};
	
	
	
	
});