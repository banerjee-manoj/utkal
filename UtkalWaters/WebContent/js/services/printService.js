app.service('printService',function($http,$rootScope){
	
	
	this.getOrderHistory= function(order){
		
		return  $http.post(hostname+'order/customerOrderHistory',order,
				{headers : {'Content-Type': 'application/json','Accept' :'application/json','AuthToken' :  $rootScope.AuthToken}})
	   .then(function success(response){
 
	   return response;
	},
	function error(response){
		 
		return response;
	}
	
	);
	
	};
	
	
	
});