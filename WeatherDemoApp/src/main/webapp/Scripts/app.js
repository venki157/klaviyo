app = angular.module('module', []);
app.controller('UserController',
	function($scope, $window, $http) {
	    $scope.email = null;
		$scope.data = {
		availableOptions: [ {id: 'Anchorage', name: 'Anchorage'},
		     				{id: 'Austin', name: 'Austin'},
		     				{id: 'Boston', name: 'Boston'},
						     {id: 'Seattle', name: 'Seattle'},
						     {id: 'Washington', name: 'Washington'}
						  ]
		};
		$scope.postdata = function (email, city) {
			var data = {
					email: email,
					city: city
			};
			//Call the Weather REST services
			var data = $http.post('/weather/register', JSON.stringify(data));
			data.then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
				if (response.data) {
					$scope.msg = response.data;
					$window.location.href = '/success.html';
				}
			  }, function errorCallback(response) {
				$window.alert("error");
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
				  $scope.msg = "Registration Failed";
					$scope.statusval = response.status;
					$scope.statustext = response.statusText;
					$scope.headers = response.headers();
					$window.location.href = '/error.html';
			  });
		}
});