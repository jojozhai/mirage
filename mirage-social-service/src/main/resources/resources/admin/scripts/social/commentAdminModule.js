'use strict';
//平台管理模块的配置
angular.module('commentAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.commentManage', {
		url: "/commentManage",
		controller: "commentManageCtrl",
		templateUrl: "admin/views/social/commentManage.html"
	});
//服务配置
}).service("commentRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.removeAll = {url:"comment/batch", method:"POST"};
	config.getReply = {url:"comment/:id/reply", method:"GET"};
	return $resource("comment/:id", {id:"@id"}, config);
//控制器
}).controller('commentManageCtrl', function($scope, $uibModal, commentRestService, commonService) {

	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		commentRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.comments = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({});
	}
	
	$scope.update = function(comment) {
		$scope.save(comment);
	}
	
	$scope.save = function(comment){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/social/commentForm.html',
			controller: 'commentFormCtrl',
			resolve: {
		        comment : function() {return comment;},
			}
		}).result.then(function(form){
			if(form.id){
				new commentRestService(form).$save().then(function(){
					commonService.showMessage("修改评论信息成功");
				},function(response){
					for (var i = 0; i < $scope.comments.length; i++) {
						if(form.id == $scope.comments[i].id) {
							$scope.comments[i] = commentRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new commentRestService(form).$create().then(function(comment){
					$scope.comments.unshift(comment);
					commonService.showMessage("新建评论成功");
				});
			}
		});
	}
	
	$scope.remove = function(comment) {
		commonService.showConfirm("您确认要删除此评论?").result.then(function() {
			commentRestService.remove({id:comment.id}).$promise.then(function(){
				commonService.showMessage("删除评论成功");
				$scope.comments.splice($scope.comments.indexOf(comment), 1);
				if($scope.comments.length == 0){
					$scope.pageInfo.page = $scope.pageInfo.page - 1;
					$scope.query();
				}
			});
		});
	} 
	
	$scope.cleanCondition = function() {
		$scope.condition = {};
		$scope.query();
	}
	
	$scope.query();
	
	$scope.commentTypes = commentTypes;
	
	$scope.choseArr = [];
	$scope.master = false; // 默认未选中
	$scope.all= function (c,v) {// 全选
		if(c==true){
			$scope.master = true;
			for (var i = 0; i < v.length; i++) {
				$scope.choseArr[i] = v[i].id;
			}
		}else{
			$scope.master = false;
			$scope.choseArr=[];
		}
	};
	
	$scope.chk = function (commentId, selected) {// 单选或者多选
		var index = -1;
		for (var i = 0; i < $scope.choseArr.length; i++) {
			var id = $scope.choseArr[i];
			if(id == commentId){
				index = i;
			}
		}
		if(selected){
			if(index == -1){
				$scope.choseArr.push(commentId);
			}
		}else{
			if(index != -1){
				$scope.choseArr.splice(index, 1);
			}
		}
	};
	
	$scope.batchDelete = function(){
		if($scope.choseArr.length == 0){
			commonService.showWarning("请选择要删除的评论");
		}else{
			commonService.showConfirm("您确认要删除所选评论?").result.then(function() {
				for (var i = 0; i < $scope.choseArr.length; i++) {
					commentRestService.removeAll({ids: $scope.choseArr}).$promise.then(function(){
						commonService.showMessage("删除评论成功");
						window.location.reload();
					});
				}
			});
		}
		
	}
	
}).controller('commentFormCtrl',function ($scope, $uibModalInstance, comment) {
	
	$scope.comment = comment;
	
	$scope.save = function(comment) {
		$uibModalInstance.close(comment);
	};
	
}).filter("commentTarget", function(){
	return function (text) {
		if(text == 'video'){
			return "视频";
		}else if(text == 'videoSet'){
			return "套餐";
		}else if(text == 'custom'){
			return "定制服务";
		}else if(text == 'shop'){
			return "体验中心";
		}else if(text == 'article'){
			return "资讯";
		}else if(text == 'activity'){
			return "线下活动";
		}else if(text == 'product'){
			return "商品";
		}else if(text == 'lesson'){
			return "课程";
		}else{
			return text;
		}
    }
});