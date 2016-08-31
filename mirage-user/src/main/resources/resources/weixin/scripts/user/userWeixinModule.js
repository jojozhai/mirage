'use strict';
//平台管理模块的配置
angular.module('userWeixinModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('userPointRank', {
		url: "/userPointRank",
		controller: "userPointRankCtrl",
		templateUrl: "weixin/views/user/userPointRank.html"
	});
//服务配置
}).service("collectRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	return $resource("collect/:id", {id:"@id"}, config);
}).service("userRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.updateMobile = {url:"user/mobile", method:"PUT"},
	config.updateProperty = {url:"user/property", method:"PUT"};
	config.updatePropertys = {url:"user/propertys", method:"PUT"};
	config.current = {method: "GET", url:"user/current"};
	return $resource("user/:id", {id:"@id"}, config);
//控制器 
}).controller('userPointRankCtrl', function($scope, userRestService, commonService) {
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	$scope.pageInfo.sort = "point,desc";
	$scope.pageInfo.size = 20;
	
	$scope.users = [];
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		userRestService.query(condition).$promise.then(function(data){
			$scope.users = $scope.users.concat(data.content);
			$scope.scrollable = true;
		});
	}
	
	$scope.scrollable = true;
	
	$scope.getNextPage = function() {
		if($scope.scrollable){
			$scope.scrollable = false;
			if($scope.users.length != 0){
				$scope.pageInfo.page = $scope.pageInfo.page + 1;
				$scope.query();
			}
		}
	}
	
	$scope.clean = function() {
		$scope.condition = {};
		$scope.users = [];
		$scope.pageInfo.page = 1;
		$scope.query();
	}
		
	
	$scope.keyup = function(e){
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
        	$scope.pageInfo.page = 1;
        	var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
    		userRestService.query(condition).$promise.then(function(data){
    			$scope.users = data.content;
    		});
        }
    };
	
	$scope.query();
	
});