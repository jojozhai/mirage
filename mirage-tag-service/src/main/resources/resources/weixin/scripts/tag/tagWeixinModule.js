'use strict';
//平台管理模块的配置
angular.module('tagWeixinModule',[]).config(function($stateProvider) {
	//路由配置
//服务配置
}).service("tagRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.getChildTags = {url:"tag/child?parentId=:parentId", method:"GET", isArray:true};
	return $resource("tag/:id", {id:"@id"}, config);
//控制器
});