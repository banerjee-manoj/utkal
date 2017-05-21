var utkalWaterHome=angular.module('utkal.waters.home', ['ui.bootstrap','ui.router']);


  


utkalWaterHome.controller('homeController',function($scope,$http,$timeout){
	$scope.user={};
	var isLoggedIn=false;
	//call for the login validation. if the validation is done then show the main container,
	//store the id/name in some variable and then check if the value is exisits or not. based on that show the main 
	//container.
	isLoggedIn=false;
	
	if(isLoggedIn){
		$("#loginDiv").hide();
	$("#landingPageDiv").show();
	}
	
$scope.login = function(){
	
	$("#loadingMessage").show();
	$scope.user.userName="Admin";
	$scope.user.password ='Admin'
	if($scope.user.userName == 'Admin' && $scope.user.password =='Admin'){
		$scope.welcomeName=$scope.user.userName;
	$("#loginDiv").hide();
	$("#landingPageDiv").show();
	$timeout(function(){$("#loadingMessage").hide();}, 300); ;
	}else {
		$scope.message="Invalid User Name or Password";
		$timeout(function(){$("#loadingMessage").hide();}, 300); ;
	}
	

	
	
	
/*	$http.defaults.headers.common['Authorization'] = 'Basic ' + $scope.user.userName + ':' + $scope.user.password;
	$http({
		method : 'POST',
		url : 'http://localhost:8080/RestSecurity/user/name/',
		data:$scope.user,
		headers : {'Content-Type': 'application/json','Accept' :'application/json'},
	}).success(function(data){
		 
	});*/
	
	
};
	
	
});



utkalWaterHome.directive('validNumber', function() {
    return {
      require: '?ngModel',
      link: function(scope, element, attrs, ngModelCtrl) {
        if(!ngModelCtrl) {
          return; 
        }

        ngModelCtrl.$parsers.push(function(val) {
          if (angular.isUndefined(val)) {
              var val = '';
          }
          
          var clean = val.replace(/[~a-z\.]|[~A-Z]*$/g, '');
          if (val !== clean) {
            ngModelCtrl.$setViewValue(clean);
            ngModelCtrl.$render();
          }
          return clean;
        });

        element.bind('keypress', function(event) {
          if(event.keyCode === 32) {
            event.preventDefault();
          }
        });
      }
    };
  });










// the below configuration is required for the URL routing of the applications.
utkalWaterHome.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/');
    
    $stateProvider
        
        // HOME STATES AND NESTED VIEWS ========================================
        .state('addCustomer', {
           // url: '/addCustomer',
            templateUrl: './html/customer/addCustomer.html'
        })
        
        
        .state('searchCustomer',{
        	
        	//url:'/searchCustomer',
        	templateUrl:'./html/customer/searchCustomer.html'
        	
        })
        
        
        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('home', {
            // we'll get to this in a bit
        	url : '/',
        	templateUrl: './html/homeContainer.html'
        })
        
        //About new order page
        .state('newOrder',{
        	//url:'/newOrder',
        	templateUrl:'./html/order/newOrder.html',
        	controller:'newOrderController'
        })
    .state('successMessage',{
    //	url: '/success',
    	templateUrl :'./html/successMessage.html',
    	params: ['message','value'],//:'hi'},
    	controller: 'successController'
    	
    })
	.state('deleteMessage',{
    //	url: '/success',
    	templateUrl :'./html/deleteMessage.html',
    	params: ['message','value'],//:'hi'},
    	controller: 'deleteMessageController'
    	
    })
	
	
    
    .state('moneyDefaulters',{
    	templateUrl :'./html/defaulters/moneyDefaulters.html',
    	controller: 'defaulterController'
    	
    })
    .state('jarDefaulters',{
    	templateUrl :'./html/defaulters/jarDefaulters.html',
    	controller: 'successController'
    	
    })
     .state('editOrder',{
    	templateUrl :'./html/order/editOrder.html',
    	//controller: 'editOrderController'
    	
    })

	 .state('orderBilling',{
    	templateUrl :'./html/order/orderHistory.html',
    	//controller: 'editOrderController'
    	
    })
	
	 .state('showAllCustomer',{
    	templateUrl :'./html/customer/showAllCustomer.html',
    	
    })
	
	 .state('deleteCustomerMessage',{
    	templateUrl :'./html/customer/showAllCustomer.html',
    	
    })
       
	.state('orderDetailsByDate',{
    	templateUrl :'./html/order/OrderDetailsByDt.html',
    	
    });	   
        
});
