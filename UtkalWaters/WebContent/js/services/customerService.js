utkalWaterHome.service('customerService',function($http,$rootScope){
	this.addCustomer= function(customer){
		var result={};
		//return  $http.post('http://localhost:8080/utkalRESTServices/customer/saveNewCustomer',customer)
		return  $http.post(hostname+'customer/saveNewCustomer',customer,
				{headers : {'Content-Type': 'application/json','Accept' :'application/json','AuthToken' : $rootScope.getToken()}})
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
		return $http.post(hostname+'customer/updateCustomer',customer,
				{headers : {'Content-Type': 'application/json','Accept' :'application/json','AuthToken' : $rootScope.getToken()}})
		.then(function success(response){
			result=response.data;
			return response;
		},function error(response){
			result =response;
			return response;
		});
	};
	
});
