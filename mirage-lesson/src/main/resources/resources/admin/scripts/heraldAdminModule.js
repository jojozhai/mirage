'use strict';
//平台管理模块的配置
angular.module('heraldAdminModule',['umeditorModule']).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.heraldManage', {
		url: "/heraldManage",
		controller: "heraldManageCtrl",
		templateUrl: "admin/views/heraldManage.html"
	});
//服务配置
}).service("heraldRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.findAll = {url:"herald/all", method:"GET", isArray:true};
	return $resource("lesson/:id", {id:"@id"}, config);
}).service("lessonUserRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	return $resource("lessonUser/:id", {id:"@id"}, config);
//控制器
}).controller('heraldManageCtrl', function($scope, $uibModal, heraldRestService, commonService) {

	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		condition.herald = true;
		condition.fromAdmin = true;
		heraldRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.heralds = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({enable:true, top: false, topIndex:0, signLimit:0, herald: true, online: true, offline: true, shareSign: false});
	}
	
	$scope.update = function(herald) {
		$scope.save(herald);
	}
	
	$scope.viewSingup = function(herald){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/heraldSignup.html',
			controller: 'heraldSignupCtrl',
			resolve: {
		        herald : function() {return herald;}
			}
		}).result.then(function(form){
			
		});
	}
	
	$scope.editContent = function(herald) {
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/umeditor.html',
			controller: 'umeditorCtrl',
			resolve: {
		        domain : function() {return herald;},
		        params : function() {
		        	return {
		        		target: 'lesson',
		        		targetId: herald.id,
		        		targetProp: 'content'
		        	}
		        }
			}
		})
	}
	
	$scope.save = function(herald){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/heraldForm.html',
			controller: 'heraldFormCtrl',
			resolve: {
		        herald : function() {return herald;},
			}
		}).result.then(function(form){
			if(form.id){
				new heraldRestService(form).$save().then(function(){
					commonService.showMessage("修改课程预告信息成功");
				},function(response){
					for (var i = 0; i < $scope.heralds.length; i++) {
						if(form.id == $scope.heralds[i].id) {
							$scope.heralds[i] = heraldRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new heraldRestService(form).$create().then(function(herald){
					$scope.heralds.unshift(herald);
					commonService.showMessage("新建课程预告成功");
				});
			}
		});
	}
	
	$scope.remove = function(herald) {
		commonService.showConfirm("您确认要删除此课程预告?").result.then(function() {
			heraldRestService.remove({id:herald.id});
		}).then(function(){
			commonService.showMessage("删除课程预告成功");
			$scope.heralds.splice($scope.heralds.indexOf(herald), 1);
			if($scope.heralds.length == 0){
				$scope.pageInfo.page = $scope.pageInfo.page - 1;
				$scope.query();
			}
		});
	} 
	
	$scope.exportXls = function(herald){
		window.open('lesson/'+herald.id+'/export');
	}
	
	$scope.cleanCondition = function() {
		$scope.condition = {};
		$scope.query();
	}
	
	$scope.query();
	
}).controller('heraldSignupCtrl',function ($scope, $uibModalInstance, herald, lessonUserRestService, commonService) {
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		condition.lessonId = herald.id;
		lessonUserRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.users = data.content;
		});
	}
	
	$scope.cleanCondition = function() {
		$scope.condition = {};
		$scope.query();
	}
	
	$scope.query();
	
}).controller('heraldFormCtrl',function ($scope, $uibModalInstance, herald, commonService, heraldRestService, tagRestService, teacherRestService, productRestService) {
	
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
	
	if(herald.id){
		$scope.herald = heraldRestService.get({id: herald.id});
	}else{
		$scope.herald = herald;
	}
	
	
	$scope.save = function(herald) {
		$uibModalInstance.close(herald);
	};
	
	$scope.teachers = teacherRestService.findAll();
	
	$scope.doUpload1 = function(files){
		commonService.uploadImage(files, $scope, function(imageUrl){
			$scope.herald.thumbnail = imageUrl;
		})		
	}
	
	$scope.doUpload2 = function(files){
		commonService.uploadImage(files, $scope, function(imageUrl){
			$scope.herald.image = imageUrl;
		})		
	}
	
});