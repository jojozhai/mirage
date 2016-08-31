'use strict';
//平台管理模块的配置
angular.module('voteAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.voteActivityManage', {
		url: "/voteActivityManage",
		controller: "voteActivityManageCtrl",
		templateUrl: "admin/views/vote/voteActivityManage.html"
	}).state('index.voteActivityForm', {
		url: "/voteActivityForm?id",
		controller: "voteActivityFormCtrl",
		templateUrl: "admin/views/vote/voteActivityForm.html"
	}).state('index.enrollManage', {
		url: "/enrollManage",
		controller: "enrollManageCtrl",
		templateUrl: "admin/views/vote/enrollManage.html"
	});
//服务配置
}).service("voteActivityRestService", function($resource, commonService){
	return $resource("voteActivity/:id", {id:"@id"}, commonService.getDefaultRestSetting());
}).service("enrollRestService", function($resource, commonService){
	return $resource("enroll/:id", {id:"@id"}, commonService.getDefaultRestSetting());
});