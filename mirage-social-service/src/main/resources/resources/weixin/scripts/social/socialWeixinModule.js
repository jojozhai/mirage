'use strict';
//平台管理模块的配置
angular.module('socialWeixinModule',[]).config(function($stateProvider) {
	
//服务配置
}).service("postRestService", function($resource, commonService){
	return $resource("post/:id", {id:"@id"}, commonService.getDefaultRestSetting());
}).service("socialRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.praise = {url:"praise", method:"POST"};
	var service = $resource("social/:id", {id:"@id"}, config);
	return service;
}).service("commentRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	var service = $resource("comment/:id", {id:"@id"}, config);
	
	service.saveComment = function(comment,  callback){
		if(isEmpty(comment.content)){
			commonService.showWarning("请填写评论内容");
			return;
		}
		service.create(comment).$promise.then(function(result){
			commonService.showMessage("评论成功");
			callback(result);
		});
	}
	
	return service;
}).directive('mirageComment', function(commonService, commentRestService) {return {
	restrict : 'A',
	link : function(scope, element, attrs) {
		
		var dataModelName = attrs["mirageComment"];
		
		scope.saveComment = function(comment){
			commentRestService.saveComment({target: dataModelName, targetId: scope[dataModelName].id, content: comment}, function(result){
				scope.comment = "";
				scope.comments.unshift(result);
			});
		}
		
	}
}});