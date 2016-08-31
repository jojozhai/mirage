'use strict';
//平台管理模块的配置
angular.module('videoSetAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.videoSetManage', {
		url: "/videoSetManage",
		controller: "videoSetManageCtrl",
		templateUrl: "admin/views/video/videoSetManage.html"
	});
//服务配置
}).service("videoSetRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.findAll = {url:"videoSet/all", method:"GET", isArray:true}
	return $resource("videoSet/:id", {id:"@id"}, config);
}).service("videosRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.query = {isArray:true};
	return $resource("videos/:id", {id:"@id"}, config);
}).controller('videoSetManageCtrl', function($scope, $uibModal, videoSetRestService, commonService) {

	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		videoSetRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.videoSets = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({
			selection: false
		});
	}
	
	$scope.update = function(videoSet) {
		$scope.save(videoSet);
	}
	
	$scope.save = function(videoSet){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/video/videoSetForm.html',
			controller: 'videoSetFormCtrl',
			resolve: {
		        videoSet : function() {
		        	return videoSet;
		        },
		        tinymceOptions : function() {return $scope.tinymceOptions;}
			}
		}).result.then(function(form){
			if(form.id){
				new videoSetRestService(form).$save().then(function(){
					commonService.showMessage("修改套餐信息成功");
				},function(response){
					for (var i = 0; i < $scope.videoSets.length; i++) {
						if(form.id == $scope.videoSets[i].id) {
							$scope.videoSets[i] = videoSetRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new videoSetRestService(form).$create().then(function(videoSet){
					$scope.videoSets.unshift(videoSet);
					commonService.showMessage("新建套餐成功");
				});
			}
		});
	}
	
	$scope.remove = function(videoSet) {
		commonService.showConfirm("您确认要删除此套餐?").result.then(function() {
			videoSetRestService.remove({id:videoSet.id});
		}).then(function(){
			commonService.showMessage("删除套餐成功");
			$scope.videoSets.splice($scope.videoSets.indexOf(videoSet), 1);
			if($scope.videoSets.length == 0){
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
	
	$scope.listVideo = function(videoSet){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/video/videoList.html',
			controller: 'videoListCtrl',
			resolve: {
		        videoSet : function() {
		        	return videoSet;
		        },
			}
		})
	}
	
}).controller('videoSetFormCtrl',function ($scope, $uibModalInstance, videoSet, uiUploader) {
	
	$scope.videoSet = videoSet;
	
	$scope.save = function(videoSet) {
		$uibModalInstance.close(videoSet);
	};
	
	$scope.doUpload = function(files){
//		console.log(files);
		uiUploader.addFiles(files);
		uiUploader.startUpload({
            url: 'image/upload',
            onCompleted: function(file, response) {
            	$scope.videoSet.image = angular.fromJson(response).content;
            	uiUploader.removeAll();
            	$scope.$apply();
            }
        });
	}
	
}).controller('videoListCtrl',function ($scope, $uibModalInstance, videoSet, videosRestService, commonService) {
	
	videosRestService.query({setId:videoSet.id}).$promise.then(function(result){
		$scope.videos = result;
	});
	
	$scope.remove = function(videos) {
		commonService.showConfirm("您确认要从套餐中移除此视频?").result.then(function() {
			videosRestService.remove({id:videos.id});
		}).then(function(){
			commonService.showMessage("移除套餐成功");
			$scope.videos.splice($scope.videos.indexOf(videos), 1);
		});
	} 
	
	$scope.moveUp = function(video){
		commonService.moveArrayItem($scope.videos, video, 'up');
		videosRestService.save($scope.videos);
	}
	
	$scope.moveDown = function(video){
		commonService.moveArrayItem($scope.videos, video, 'down');
		videosRestService.save($scope.videos);
	}
	
});