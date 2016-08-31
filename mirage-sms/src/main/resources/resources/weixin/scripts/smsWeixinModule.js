'use strict';
// 平台管理模块的配置
angular.module('smsWeixinModule', []).config(function($stateProvider) {


// 服务配置
}).service("smsRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.sendSmsCode = {url: "sms/code", method: "GET"};
	config.checkSmsCode = {url: "sms/code/check", method: "GET"};
	return $resource("sms/:id", {id:"@id"}, config);
});