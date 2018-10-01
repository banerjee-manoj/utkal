utkalWaterHome.service('orderHistoryService',function($http,$rootScope){
	
	
	this.getOrderHistory= function(order){
		
		return  $http.post(hostname+'order/customerOrderHistory',order,
				{headers : {'Content-Type': 'application/json','Accept' :'application/json','AuthToken' : $rootScope.getToken()}})
	   .then(function success(response){
 
	   return response;
	},
	function error(response){
		 
		return response;
	}
	
	);
	
	};
	
	// Get the order Details and history by date...
	
	this.getOrderDetailsByDate = function(order){
		
		
		return  $http.post(hostname+'order/orderDetailsByDate',order,
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