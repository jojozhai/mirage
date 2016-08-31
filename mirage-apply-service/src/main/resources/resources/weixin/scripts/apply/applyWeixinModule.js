'use strict';
//平台管理模块的配置
angular.module('applyWeixinModule',[]).config(function($stateProvider) {
	
//服务配置
}).service("applyRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	return $resource("apply/:id", {id:"@id"}, config);
});