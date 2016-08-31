'use strict';
//平台管理模块的配置
angular.module('challengeTaskManageModule', []).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.challengeTaskManage', {
		url: "/challengeTaskManage?id",
		controller: "challengeTaskManageCtrl",
		templateUrl: "admin/views/challenge/challengeTaskManage.html"
	});
//服务配置
}).service("challengeTaskRestService", function($resource, commonService){
	return $resource("challengeTask/:id", {id:"@id"}, commonService.getDefaultRestSetting());
//控制器
}).controller('challengeTaskManageCtrl', function($scope, $uibModal, $stateParams, challengeTaskRestService, challengeRestService, commonService){
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.challengeTasks = [];
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		condition.challengeId = $stateParams.id;
		challengeTaskRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.challengeTasks = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({});
	}
	
	$scope.update = function(challengeTask) {
		$scope.save(challengeTask);
	}
	
	$scope.save = function(challengeTask){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/challenge/challengeTaskForm.html',
			controller: 'challengeTaskFormCtrl',
			resolve: {
				challenge : function() {return challengeRestService.get({id:$stateParams.id});},
		        challengeTask : function() {return challengeTask;},
		        tinymceOptions : function() {return $scope.tinymceOptions;}
			}
		}).result.then(function(form){
			if(form.id){
				new challengeTaskRestService(form).$save().then(function(){
					commonService.showMessage("修改挑战任务信息成功");
				},function(response){
					for (var i = 0; i < $scope.challengeTasks.length; i++) {
						if(form.id == $scope.challengeTasks[i].id) {
							$scope.challengeTasks[i] = challengeTaskRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new challengeTaskRestService(form).$create().then(function(challengeTask){
					$scope.challengeTasks.unshift(challengeTask);
					commonService.showMessage("新建挑战任务成功");
				});
			}
		});
	}
	
	$scope.remove = function(challengeTask) {
		commonService.showConfirm("您确认要删除此挑战任务?").result.then(function() {
			challengeTaskRestService.remove({id:challengeTask.id});
		}).then(function(){
			commonService.showMessage("删除挑战任务成功");
			$scope.challengeTasks.splice($scope.challengeTasks.indexOf(challengeTask), 1);
			if($scope.challengeTasks.length == 0){
				$scope.pageInfo.page = $scope.pageInfo.page - 1;
				$scope.query();
			}
		});
	} 
	
	$scope.cleanCondition = function() {
		$scope.condition = {};
		$scope.query();
	}
	
	$scope.query();
	
}).controller('challengeTaskFormCtrl',function ($scope, $uibModalInstance, tinymceOptions, challenge, challengeTask, uiUploader) {

	$scope.challenge = challenge;
	$scope.challengeTask = challengeTask;
	$scope.tinymceOptions = tinymceOptions;
	
	$scope.save = function(challengeTask) {
		challengeTask.challengeId = $scope.challenge.id;
		challengeTask.challengeName = $scope.challenge.name;
		$uibModalInstance.close(challengeTask);
	};
	
	$scope.doUpload = function(files){
//		console.log(files);
		uiUploader.addFiles(files);
		uiUploader.startUpload({
	        url: 'image/upload',
	        onCompleted: function(file, response) {
	        	$scope.challengeTask.image = angular.fromJson(response).content;
	        	uiUploader.removeAll();
	        	$scope.$apply();
	        }
	    });
	}
	
});