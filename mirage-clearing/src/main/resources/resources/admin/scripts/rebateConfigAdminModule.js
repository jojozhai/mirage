'use strict';
//平台管理模块的配置
angular.module('rebateConfigAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.rebateConfigManage', {
		url: "/rebateConfigManage",
		controller: "rebateConfigManageCtrl",
		templateUrl: "admin/views/rebateConfigManage.html"
	});
//服务配置
}).service("rebateConfigRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.findAll = {url:"rebateConfig/all", method:"GET", isArray:true};
	return $resource("rebateConfig/:id", {id:"@id"}, config);
//控制器
}).controller('rebateConfigManageCtrl', function($scope, $uibModal, rebateConfigRestService, commonService) {

	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		rebateConfigRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.rebateConfigs = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({});
	}
	
	$scope.update = function(rebateConfig) {
		$scope.save(rebateConfig);
	}
	
	$scope.save = function(rebateConfig){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/rebateConfigForm.html',
			controller: 'rebateConfigFormCtrl',
			resolve: {
		        rebateConfig : function() {return rebateConfig;},
			}
		}).result.then(function(form){
			if(form.id){
				new rebateConfigRestService(form).$save().then(function(){
					commonService.showMessage("修改配置成功");
				},function(response){
					for (var i = 0; i < $scope.rebateConfigs.length; i++) {
						if(form.id == $scope.rebateConfigs[i].id) {
							$scope.rebateConfigs[i] = rebateConfigRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new rebateConfigRestService(form).$create().then(function(rebateConfig){
					$scope.rebateConfigs.unshift(rebateConfig);
					commonService.showMessage("新建配置成功");
				});
			}
		});
	}
	
	$scope.remove = function(rebateConfig) {
		commonService.showConfirm("您确认要删除此配置?").result.then(function() {
			rebateConfigRestService.remove({id:rebateConfig.id});
		}).then(function(){
			commonService.showMessage("删除配置成功");
			$scope.rebateConfigs.splice($scope.rebateConfigs.indexOf(rebateConfig), 1);
			if($scope.rebateConfigs.length == 0){
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
	
}).controller('rebateConfigFormCtrl',function ($scope, $uibModalInstance, rebateConfig, commonService) {
	
	$scope.rebateConfig = rebateConfig;
	
	$scope.tinymceOptions = commonService.getDefaultTinymceOptions();
	
	$scope.save = function(rebateConfig) {
		$uibModalInstance.close(rebateConfig);
	};
	
	$scope.doUpload = function(files){
		commonService.uploadImage(files, $scope, function(imageUrl){
			$scope.rebateConfig.image = imageUrl;
		})		
	}
	
});