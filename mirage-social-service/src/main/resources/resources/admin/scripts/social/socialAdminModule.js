'use strict';
//平台管理模块的配置
angular.module('socialAdminModule',[]).config(function($stateProvider) {
	
//服务配置
}).service("postRestService", function($resource, commonService){
	return $resource("post/:id", {id:"@id"}, commonService.getDefaultRestSetting());
}).service("socialRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.removeComment = {url:"comment/delete", method:"POST"};
	return $resource("social/:id", {id:"@id"}, config);
}).controller('commentListCtrl',function ($scope, commentable, target, targetId, socialRestService, commonService) {
	
	$scope.commentable = commentable;
	
	$scope.isConditionCollapsed = true;
	
	$scope.remove = function(comment){
		commonService.showConfirm("您确认要删除此评论?").result.then(function() {
			socialRestService.removeComment({
				id: comment.id,
				target: target,
				targetId: targetId
			}).$promise.then(function(){
				commonService.showMessage("删除评论成功");
				$scope.commentable.comments.splice($scope.commentable.comments.indexOf(comment), 1);
			});
		});
	}
	
});