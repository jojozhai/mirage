'use strict';
//平台管理模块的配置
angular.module('posterWeixinModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('posterActivityDesc', {
		url: "/posterActivityDesc",
		controller: "posterActivityDescCtrl",
		templateUrl: "weixin/views/poster/posterActivityDesc.html"
	});
//服务配置
}).service("posterRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.getActivityDesc = {method: "GET", url: "poster/desc"};
	return $resource("poster/:id", {id:"@id"}, config);
//控制器
}).controller('posterActivityDescCtrl', function($scope, posterRestService) {
	
	$scope.param = posterRestService.getActivityDesc();
	
});