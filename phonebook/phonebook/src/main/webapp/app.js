var app = angular.module('phonebookApp',['ngRoute'])

app.controller('RouteController', function($scope){
	$scope.routemessage = 'The RouteController';
})

.controller('WelcomeController', function($scope){
	$scope.message = 'Hello From the WelcomeController';
})

.controller('CaseController', function($scope, $http){
	$scope.upperCaseText='in ng-controller : CaseController';
	
	$scope.callUpperCase = function(){
		console.log("Onclick :: callUpperCase :: called ");
		$http({
			method: "POST",
			url: "/toUpperCase",
			headers: {
				'Content-Type': 'application/json'
			},
			data: {'guestName' : $scope.guestName }
		}).then(
			function (response){
				$scope.upperCaseText=response.data.responseData;
				//location.href="#!/home";
			},
			function (response){
				console.log('Error occurred ')
			}		
		);
	}
	
})

.config(function($routeProvider){
	//console.log("inside :: config called")
	$routeProvider
		.when('/login',{
			templateUrl : 'pages/login.html',
			controller : 'RouteController'
		})
		.when('/',{
			templateUrl : 'pages/home.html',
			controller : 'RouteController'
		})
		.when('/home',{
			templateUrl : 'pages/home.html',
			controller : 'RouteController'
		})
		.when('/dashboard',{
			templateUrl : 'pages/dashboard.html',
			controller : 'RouteController'
		})
		.when('/about',{
			templateUrl : 'pages/about.html',
			controller : 'RouteController'
		})
		.when('/code',{
			templateUrl : 'pages/code.html',
			controller : 'RouteController'
		})
		.otherwise({redirectTo: '/'});
});

