app.service('printService',function($http){
	
	
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
	
	
	
});