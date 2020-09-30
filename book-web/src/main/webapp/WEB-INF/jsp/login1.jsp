<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en-US">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<body>

	<div ng-app="myApp" ng-controller="myCtrl">

		Username: <input type="text" ng-model="username"><br>
		Password: <input type="password" ng-model="password"><br>
		<br>

		<button ng-click="loginFunction()">Login</button>

		<!-- {{username + " " + password}} -->
		<!-- <p>{{ count }}</p> -->
		<div>${errorMessage}</div>

		<script>
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope, $http) {

				$scope.loginFunction = function() {
					/* $scope.count++; */
					$http({
					    method : "POST",
					      url : "/login1",
					      headers: {
					    	   'Content-Type': 'application/json'
					    	 },
					      data: //angular.toJson(
					    		  {
									'firstname':'', 
									'lastname':'', 
									'email':'',
									'username':$scope.username, 
									'password':$scope.password
					    		  }
					    		//)
					  }).then(function mySuccess(response) {
					    location.pathname = '/welcome';
					  }, function myError(response) {
					    location.pathname = '/login1';
					  });
				};
			
			});
		</script>
		
		<script src="/webjars/angularjs/1.8.0/angular.js" />
</body>
</html>