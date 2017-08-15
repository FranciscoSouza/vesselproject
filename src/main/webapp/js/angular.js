var app = angular.module('app', []);

app.controller('postController', function($scope, $http, $location) {
	$scope.submitForm = function(){
		
		
		var config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
        }
		
		var data = {
            lat: $scope.lat,
            lng: $scope.lng
        };
		
		var url = $location.absUrl()+'/timeForLatLng'+'?lng='+$scope.lng+'&lat='+$scope.lat;
		
		
		$http.post(url, data, config).then(function (response) {
			$scope.postResultMessage = response.data;
		}, function (response) {
			$scope.postResultMessage = "Fail!";
		});
		
		$scope.lat = "";
		$scope.lng = "";
	}
});
