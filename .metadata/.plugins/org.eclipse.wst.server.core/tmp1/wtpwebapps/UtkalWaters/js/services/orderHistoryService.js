utkalWaterHome.service('orderHistoryService',function($http){
	
	
	this.getOrderHistory= function(order){
		
		return  $http.post(hostname+'order/customerOrderHistory',order)
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
		
		
		return  $http.post(hostname+'order/orderDetailsByDate',order)
	   .then(function success(response){
 
	   return response;
	},
	function error(response){
		 
		return response;
	}
	
	);
		
		
		
	};
	
	
	
});