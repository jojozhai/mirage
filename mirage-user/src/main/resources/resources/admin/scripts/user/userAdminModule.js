'use strict';
//平台管理模块的配置
angular.module('userAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.userManage', {
		url: "/userManage",
		controller: "userManageCtrl",
		templateUrl: "admin/views/user/userManage.html"
	});
//服务配置
}).service("userRestService", function($resource, commonService){
	return $resource("user/:id", {id:"@id"}, commonService.getDefaultRestSetting());
//控制器
}).controller('userManageCtrl', function($scope, userRestService, commonService) {
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.isConditionCollapsed = true;
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		userRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.users = data.content;
		});
	}
	
	$scope.cleanCondition = function() {
		$scope.condition = {};
		$scope.query();
	}
	
	$scope.query();
	
});