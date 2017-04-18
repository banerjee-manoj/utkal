utkalWaterHome.service('orderService',function($http){
	
	
	//Load the customer List in the combo box
	this.loadCustomerList= function(){
		return  $http.get(hostname+'customer/customerListJson')
	   .then(function success(response){
 
	   return response;
	},
	function error(response){
		 
		return response;
	}
	
	);
	
	};
	
	
	
	//Load customer Details for the oder page
	this.loadCustomerDetails= function(key){
		return  $http.get(hostname+'customer/customerDetails/'+key)
	   .then(function success(response){
	   return response;
	},
	function error(response){
		return response;
	}
	);
		};
		
		
		
		// get the payment details for the customer :
		
		this.getPaymentDetails = function(key){
			
			return $http.get(hostname+'order/customerPaymentDetails/'+key)
			.then(function succes(response){
				return response;
			},
			function error(response){
				return response;
			});
			
		};
		
		
		
		
		
		
		
		this.newOrder = function(order){
			
			return $http.post(hostname+'order/newOrder',order)
			.then(function success(response){
				return response;
			},
			function error(response){
				return response;
			}
			);
			
		};
	
	
	// get the order details by customer id and order date:
	
	    this.getOrderDetailsByDateAndCustomerId = function(order){
			return $http.post(hostname+'order/getOrderByDateAndCustomerId',order)
			.then(function success(response){
				return response;
			},
			function error(response){
				return response;
			}
			);
			
		};
		
		//update the order details ...
		
		this.editCustomerOrder = function(order){
			return $http.post(hostname+'order/updateOrder',order)
			.then(function success(response){
				return response;
			},
			function error(response){
				return response;
			}
			);
			
		};
	
});
