'use strict';
//平台管理模块的配置
angular.module('lessonAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.lessonManage', {
		url: "/lessonManage",
		controller: "lessonManageCtrl",
		templateUrl: "admin/views/lessonManage.html"
	});
//服务配置
}).service("lessonRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.findAll = {url:"lesson/all", method:"GET", isArray:true};
	return $resource("lesson/:id", {id:"@id"}, config);
//控制器
}).controller('lessonManageCtrl', function($scope, $uibModal, lessonRestService, commonService) {

	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		lessonRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.lessons = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({enable:true});
	}
	
	$scope.update = function(lesson) {
		$scope.save(lesson);
	}
	
	$scope.save = function(lesson){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/lessonForm.html',
			controller: 'lessonFormCtrl',
			resolve: {
		        lesson : function() {return lesson;},
			}
		}).result.then(function(form){
			if(form.id){
				new lessonRestService(form).$save().then(function(){
					commonService.showMessage("修改课程信息成功");
				},function(response){
					for (var i = 0; i < $scope.lessons.length; i++) {
						if(form.id == $scope.lessons[i].id) {
							$scope.lessons[i] = lessonRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new lessonRestService(form).$create().then(function(lesson){
					$scope.lessons.unshift(lesson);
					commonService.showMessage("新建课程成功");
				});
			}
		});
	}
	
	$scope.remove = function(lesson) {
		commonService.showConfirm("您确认要删除此课程?").result.then(function() {
			lessonRestService.remove({id:lesson.id});
		}).then(function(){
			commonService.showMessage("删除课程成功");
			$scope.lessons.splice($scope.lessons.indexOf(lesson), 1);
			if($scope.lessons.length == 0){
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
	
}).controller('lessonFormCtrl',function ($scope, $uibModalInstance, lesson, commonService, lessonRestService, tagRestService, teacherRestService) {
	
	$scope.popup1 = {
		opened : false
	};

	$scope.popup2 = {
		opened : false
	};
	
	$scope.popup3 = {
		opened : false
	};

	$scope.popup4 = {
		opened : false
	};
	
	$scope.open1 = function() {
		$scope.popup1.opened = true;
	};

	$scope.open2 = function() {
		$scope.popup2.opened = true;
	};
	
	$scope.open3 = function() {
		$scope.popup3.opened = true;
	};

	$scope.open4 = function() {
		$scope.popup4.opened = true;
	};
	
	$scope.dateOptions = {
		startingDay : 1
	};
	
	if(lesson.id){
		$scope.lesson = lessonRestService.get({id: lesson.id});
	}else{
		$scope.lesson = lesson;
	}
	
	
	$scope.save = function(lesson) {
		$uibModalInstance.close(lesson);
	};
	
	$scope.typeTags = tagRestService.getChildTags({parentId:1});
	$scope.setTags = tagRestService.getChildTags({parentId:2});
	$scope.teachers = teacherRestService.findAll();
	
	$scope.doUpload1 = function(files){
		commonService.uploadImage(files, $scope, function(imageUrl){
			$scope.lesson.thumbnail = imageUrl;
		})		
	}
	
	$scope.doUpload2 = function(files){
		commonService.uploadImage(files, $scope, function(imageUrl){
			$scope.lesson.image = imageUrl;
		})		
	}
	
});