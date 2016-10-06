'use strict';
//平台管理模块的配置
angular.module('teacherAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.teacherManage', {
		url: "/teacherManage",
		controller: "teacherManageCtrl",
		templateUrl: "admin/views/teacherManage.html"
	});
//服务配置
}).service("teacherRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.findAll = {url:"teacher/all", method:"GET", isArray:true};
	return $resource("teacher/:id", {id:"@id"}, config);
//控制器
}).controller('teacherManageCtrl', function($scope, $uibModal, teacherRestService, commonService) {

	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		teacherRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.teachers = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({saleCount: 0, saleCountPlus: 100});
	}
	
	$scope.update = function(teacher) {
		$scope.save(teacher);
	}
	
	$scope.save = function(teacher){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/teacherForm.html',
			controller: 'teacherFormCtrl',
			resolve: {
		        teacher : function() {return teacher;},
			}
		}).result.then(function(form){
			if(form.id){
				new teacherRestService(form).$save().then(function(){
					commonService.showMessage("修改老师信息成功");
				},function(response){
					for (var i = 0; i < $scope.teachers.length; i++) {
						if(form.id == $scope.teachers[i].id) {
							$scope.teachers[i] = teacherRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new teacherRestService(form).$create().then(function(teacher){
					$scope.teachers.unshift(teacher);
					commonService.showMessage("新建老师成功");
				});
			}
		});
	}
	
	$scope.remove = function(teacher) {
		commonService.showConfirm("您确认要删除此老师?").result.then(function() {
			teacherRestService.remove({id:teacher.id});
		}).then(function(){
			commonService.showMessage("删除老师成功");
			$scope.teachers.splice($scope.teachers.indexOf(teacher), 1);
			if($scope.teachers.length == 0){
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
	
}).controller('teacherFormCtrl',function ($scope, $uibModalInstance, teacher, commonService) {
	
	$scope.teacher = teacher;
	
	$scope.tinymceOptions = commonService.getDefaultTinymceOptions();
	
	$scope.save = function(teacher) {
		$uibModalInstance.close(teacher);
	};
	
	$scope.doUpload = function(files){
		commonService.uploadImage(files, $scope, function(imageUrl){
			$scope.teacher.image = imageUrl;
		})		
	}
	
});