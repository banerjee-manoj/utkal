utkalWaterHome.service('customerService',function($http){
	this.addCustomer= function(customer){
		var result={};
		//return  $http.post('http://localhost:8080/utkalRESTServices/customer/saveNewCustomer',customer)
		return  $http.post(hostname+'customer/saveNewCustomer',customer)
	   .then(function success(response){
            result=response.data;
            return response;
	},
	function error(response){
		 result=response;
		return response;
	}
	);
	};

	
	this.updateCustomer = function(customer){
		var result={}; 
		//return $http.post('http://localhost:8080/utkalRESTServices/customer/updateCustomer',customer)
		return $http.post(hostname+'customer/updateCustomer',customer)
		.then(function success(response){
			result=response.data;
			return response;
		},function error(response){
			result =response;
			return response;
		});
	};
	
});
