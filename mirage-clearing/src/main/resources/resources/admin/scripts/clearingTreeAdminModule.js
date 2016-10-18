'use strict';
//平台管理模块的配置
angular.module('clearingTreeAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.clearingTreeManage', {
		url: "/clearingTreeManage",
		controller: "clearingTreeManageCtrl",
		templateUrl: "admin/views/clearingTreeManage.html"
	});
//服务配置
}).service("clearingTreeRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.findAll = {url:"clearingTree/all", method:"GET", isArray:true};
	return $resource("clearingTree/:id", {id:"@id"}, config);
//控制器
}).controller('clearingTreeManageCtrl', function($scope, $uibModal, clearingTreeRestService, commonService) {

	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		clearingTreeRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.clearingTrees = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({});
	}
	
	$scope.update = function(clearingTree) {
		$scope.save(clearingTree);
	}
	
	$scope.save = function(clearingTree){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/clearingTreeForm.html',
			controller: 'clearingTreeFormCtrl',
			resolve: {
		        clearingTree : function() {return clearingTree;},
			}
		}).result.then(function(form){
			if(form.id){
				new clearingTreeRestService(form).$save().then(function(){
					commonService.showMessage("修改配置成功");
				},function(response){
					for (var i = 0; i < $scope.clearingTrees.length; i++) {
						if(form.id == $scope.clearingTrees[i].id) {
							$scope.clearingTrees[i] = clearingTreeRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new clearingTreeRestService(form).$create().then(function(clearingTree){
					$scope.clearingTrees.unshift(clearingTree);
					commonService.showMessage("新建配置成功");
				});
			}
		});
	}
	
	$scope.remove = function(clearingTree) {
		commonService.showConfirm("您确认要删除此配置?").result.then(function() {
			clearingTreeRestService.remove({id:clearingTree.id});
		}).then(function(){
			commonService.showMessage("删除配置成功");
			$scope.clearingTrees.splice($scope.clearingTrees.indexOf(clearingTree), 1);
			if($scope.clearingTrees.length == 0){
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
	
}).controller('clearingTreeFormCtrl',function ($scope, $uibModalInstance, clearingTree, commonService) {
	
	$scope.clearingTree = clearingTree;
	
	$scope.tinymceOptions = commonService.getDefaultTinymceOptions();
	
	$scope.save = function(clearingTree) {
		$uibModalInstance.close(clearingTree);
	};
	
	$scope.doUpload = function(files){
		commonService.uploadImage(files, $scope, function(imageUrl){
			$scope.clearingTree.image = imageUrl;
		})		
	}
	
});