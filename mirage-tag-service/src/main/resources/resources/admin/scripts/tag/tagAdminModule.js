'use strict';
//平台管理模块的配置
angular.module('tagAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.tagManage', {
		url: "/tagManage?id",
		controller: "tagManageCtrl",
		templateUrl: "admin/views/tag/tagManage.html"
	});
//服务配置
}).service("tagRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.moveUp = {url:"tag/:id/move/up", method:"POST"};
	config.moveDown = {url:"tag/:id/move/down", method:"POST"};
	config.toOptions = {url:"tag/options", method:"GET", isArray:true};
	config.getTags = {url:"tags", method:"GET", isArray:true};
	config.getTagInfos = {url:"tags/info", method:"GET", isArray:true};
	config.getChildTags = {url:"tag/child?parentId=:parentId", method:"GET", isArray:true};
	return $resource("tag/:id", {id:"@id"}, config);
//控制器
}).controller('tagManageCtrl', function($scope, $stateParams, $uibModal, tagRestService, commonService) {

	$scope.treeOptions = commonService.getDefaultTreeSetting();
	
	$scope.treedata = tagRestService.query({rootId: $stateParams.id});
	
	$scope.expandedNodes = [];
	
	$scope.create = function() {
		$scope.save({parentId: $stateParams.id, hot: 0}); 
	}
	
	$scope.update = function(tag) {
		$scope.save(tag);
	}
	
	$scope.selecteMenu = function(node, selected) {
		if(selected) {
			$scope.expandedNodes.push(node);
		}else{
			for (var i = 0; i < $scope.expandedNodes.length; i++) {
				if($scope.expandedNodes[i].id == node.id) {
					$scope.expandedNodes.splice($scope.expandedNodes.indexOf(node), 1);
				}
			}
		}
		$scope.selected = selected;
	}
	
	$scope.save = function(tag){
		$uibModal.open({
			templateUrl : 'admin/views/tag/tagForm.html',
			controller: 'tagFormCtrl',
			resolve: {
		        tag : function() {return tag;}
			}
		}).result.then(function(formInfo){
			if($scope.selected){
				formInfo.parentId = $scope.tag.id;
			}
			if(formInfo.id){
				new tagRestService(formInfo).$save().then(function(){
					commonService.showMessage("修改分类信息成功");
				});
			}else{
				new tagRestService(formInfo).$create().then(function(tag){
					commonService.addNode($scope.treedata.children, $scope.tag, tag);
					commonService.showMessage("新建分类成功");
				});
			}
		});
	}
	
	$scope.remove = function(tag) {
		commonService.showConfirm("您确认要删除此分类?", "所有的子分类会被一并删除").result.then(function() {
			tagRestService.remove({id:tag.id});
		}).then(function(){
			commonService.removeNode($scope.treedata.children, tag.id);
			$scope.tag = {};
			$scope.selecteMenu(tag, false);
			commonService.showMessage("删除分类项成功");
		});
	} 
	
	$scope.moveUp = function(tag) {
		tagRestService.moveUp({id:tag.id}).$promise.then(function(result){
			commonService.moveNode($scope.treedata, result.content, tag, "up");
		});
	}
	
	$scope.moveDown = function(tag) {
		tagRestService.moveDown({id:tag.id}).$promise.then(function(result){
			commonService.moveNode($scope.treedata, result.content, tag, "down");
		});
	}
	
}).controller('tagFormCtrl',function ($scope, $uibModalInstance, tag, uiUploader) {
	$scope.tag = tag;
	$scope.save = function(tag) {
		$uibModalInstance.close(tag);
	};
	$scope.doUpload = function(files){
//		console.log(files);
		uiUploader.addFiles(files);
		uiUploader.startUpload({
            url: 'image/upload',
            onCompleted: function(file, response) {
            	$scope.tag.image = angular.fromJson(response).content;
            	uiUploader.removeAll();
            	$scope.$apply();
            }
        });
	}
});