angular.module('wordcloud', ['angular-jqcloud'])
  .controller('wordcloudController', function($scope, $http) {
	  
	  // define the ng-model to store the UI input
	  $scope.url = {};
	  
	  // define the method used to deal with the request of generating a Word Cloud
	  $scope.generateWorldCloud = function() {
		  var headers = url ? {url : url.value} : {url : ''};
		  console.log(url);
	      $http.get('/wordcloud/', {headers : headers}).success(function(data) {
			  $scope.words = eval(data.words);
		  })
	  };
});