'use strict';
//平台管理模块的配置
angular.module('challengeManageModule', []).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.challengeManage', {
		url: "/challengeManage",
		controller: "challengeManageCtrl",
		templateUrl: "admin/views/challenge/challengeManage.html"
	});
//服务配置
}).service("challengeRestService", function($resource, commonService){
	return $resource("challenge/:id", {id:"@id"}, commonService.getDefaultRestSetting());
//控制器
}).controller('challengeManageCtrl', function($scope, $state, $uibModal, challengeRestService, commonService){
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		challengeRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.challenges = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({
			days:0,
			participatorCount:0,
			taskCount:0
		});
	}
	
	$scope.update = function(challenge) {
		$scope.save(challenge);
	}
	
	$scope.save = function(challenge){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/challenge/challengeForm.html',
			controller: 'challengeFormCtrl',
			resolve: {
		        challenge : function() {return challenge;},
		        tinymceOptions : function() {return $scope.tinymceOptions;}
			}
		}).result.then(function(form){
			if(form.id){
				new challengeRestService(form).$save().then(function(){
					commonService.showMessage("修改挑战信息成功");
				},function(response){
					for (var i = 0; i < $scope.challenges.length; i++) {
						if(form.id == $scope.challenges[i].id) {
							$scope.challenges[i] = challengeRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new challengeRestService(form).$create().then(function(challenge){
					$scope.challenges.unshift(challenge);
					commonService.showMessage("新建挑战成功");
				});
			}
		});
	}
	
	$scope.remove = function(challenge) {
		commonService.showConfirm("您确认要删除此挑战?").result.then(function() {
			challengeRestService.remove({id:challenge.id});
		}).then(function(){
			commonService.showMessage("删除挑战成功");
			$scope.challenges.splice($scope.challenges.indexOf(challenge), 1);
			if($scope.challenges.length == 0){
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
	
	$scope.taskManage = function(challenge) {
		$state.go("index.challengeTaskManage", {id:challenge.id});
	}
	
}).controller('challengeFormCtrl',function ($scope, $uibModalInstance, tinymceOptions, challenge, uiUploader, teacherRestService) {

	$scope.challenge = challenge;
	$scope.teachers = teacherRestService.findAll();
	$scope.tinymceOptions = tinymceOptions;
	
	$scope.save = function(challenge) {
		$uibModalInstance.close(challenge);
	};
	
	$scope.doUpload = function(files){
//		console.log(files);
		uiUploader.addFiles(files);
		uiUploader.startUpload({
	        url: 'image/upload',
	        onCompleted: function(file, response) {
	        	$scope.challenge.image = angular.fromJson(response).content;
	        	uiUploader.removeAll();
	        	$scope.$apply();
	        }
	    });
	}
	
});