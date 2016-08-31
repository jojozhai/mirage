'use strict';
//平台管理模块的配置
angular.module('posterAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.posterManage', {
		url: "/posterManage",
		controller: "posterManageCtrl",
		templateUrl: "admin/views/poster/posterManage.html"
	}).state('index.posterDesc', {
		url: "/posterDesc",
		controller: "posterDescCtrl",
		templateUrl: "admin/views/poster/posterDesc.html"
	});
//服务配置
}).service("posterRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.getActivityDesc = {method: "GET", url: "poster/desc"};
	config.saveActivityDesc = {method: "POST", url: "poster/desc"};
	return $resource("poster/:id", {id:"@id"}, config);
}).service("userPosterRestService", function($resource, commonService){
	return $resource("userPoster/:id", {id:"@id"}, commonService.getDefaultRestSetting());
//控制器
}).controller('posterManageCtrl', function($scope, $uibModal, posterRestService, commonService){
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		posterRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.posters = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({
			headTop: 100,
			headLeft: 100,
			headScale: 35,
			qrcodeTop: 100,
			qrcodeLeft: 100,
			qrcodeScale: 35,
			onlyNewUserAddPoint: false,
			incircleHead: false,
			activePoint: 1
		});
	}
	
	$scope.update = function(poster) {
		$scope.save(poster);
	}
	
	$scope.save = function(poster){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/poster/posterForm.html',
			controller: 'posterFormCtrl',
			resolve: {
		        poster : function() {return poster;}
			}
		}).result.then(function(form){
			if(form.id){
				new posterRestService(form).$save().then(function(){
					commonService.showMessage("修改海报信息成功");
				},function(response){
					for (var i = 0; i < $scope.posters.length; i++) {
						if(form.id == $scope.posters[i].id) {
							$scope.posters[i] = posterRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new posterRestService(form).$create().then(function(poster){
					$scope.posters.unshift(poster);
					commonService.showMessage("新建海报成功");
				});
			}
		});
	}
	
	$scope.remove = function(poster) {
		commonService.showConfirm("您确认要删除此海报?").result.then(function() {
			posterRestService.remove({id:poster.id});
		}).then(function(){
			commonService.showMessage("删除海报成功");
			$scope.posters.splice($scope.posters.indexOf(poster), 1);
			if($scope.posters.length == 0){
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
	
	$scope.rank = function(poster){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/poster/userPosterManage.html',
			controller: 'userPosterManageCtrl',
			resolve: {
		        poster : function() {return poster;}
			}
		});
	}
	
}).controller('userPosterManageCtrl',function ($scope, poster, commonService, userPosterRestService){
	
	$scope.isConditionCollapsed = true;
	$scope.condition = {posterId:poster.id};
	$scope.pageInfo = commonService.getDefaultPageSetting();
	$scope.pageInfo.sort = "pointCount,desc"; 
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		userPosterRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.userPosters = data.content;
		});
	}
	
	$scope.remove = function(userPoster) {
		commonService.showConfirm("您确认要删除此海报?").result.then(function() {
			userPosterRestService.remove({id:userPoster.id});
		}).then(function(){
			commonService.showMessage("删除海报成功");
			$scope.userPosters.splice($scope.userPosters.indexOf(userPoster), 1);
			if($scope.userPosters.length == 0){
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
	
}).controller('posterFormCtrl',function ($scope, $uibModalInstance, poster, uiUploader) {

	$scope.poster = poster;
	
	$scope.save = function(poster) {
		$uibModalInstance.close(poster);
	};
	
	$scope.doUpload = function(files){
//		console.log(files);
		uiUploader.addFiles(files);
		uiUploader.startUpload({
            url: 'image/upload',
            onCompleted: function(file, response) {
            	$scope.poster.image = angular.fromJson(response).content;
            	uiUploader.removeAll();
            	$scope.$apply();
            }
        });
	}
	
}).controller('posterDescCtrl',function ($scope, posterRestService) {

	$scope.param = posterRestService.getActivityDesc();
	
	$scope.save = function(param) {
		posterRestService.saveActivityDesc(param).$promise.then(function(){
			commonService.showMessage("修改海报信息成功");
		});
	};
	
});