utkalWaterHome.service('defaulterService',function($http){
	
	
	
	//Load the data for the Payment Defaulters
	this.paymentDefaulterList= function(){
		return  $http.get('http://localhost:8080/utkalRESTServices/defaulters/paymentDefaulterList')
	   .then(function success(response){
	   return response;
	},
	function error(response){
		 
		return response;
	}
	
	);
	
	};
	
	//Load the data for the Jar Defaulters
	this.jarDefaulterList= function(){
		return  $http.get(hostname+'defaulters/jarDefaulterList')
	   .then(function success(response){
	   return response;
	},
	function error(response){
		 
		return response;
	}
	
	);
	
	};
	
	//Fetch the data for the payment and jar defaulters.
	this.defaulterList= function(){
		$("#loadingMessage").show();
		return  $http.get(hostname+'defaulters/defaulterList')
	   .then(function success(response){
	   return response;
	},
	function error(response){
		 
		return response;
	}
	
	);
	
	};
	
	
	//Load the data for the pending of the jar and payment and container Defaulters
	this.getPrevDetailsByCustomer= function(key){
		return  $http.get(hostname+'defaulters/prevDueByCustomer/'+key)
	   .then(function success(response){
	   return response;
	},
	function error(response){
		 
		return response;
	}
	
	);
	
	};
	
	
	//Save the pendiing details table
	this.savePendingDetails= function(prevDtls){
		return  $http.post(hostname+'defaulters/savePendingData',prevDtls)
	   .then(function success(response){
	   return response;
	},
	function error(response){
		 
		return response;
	}
	
	);
	
	};
	
	
	
	
	
	
	
});